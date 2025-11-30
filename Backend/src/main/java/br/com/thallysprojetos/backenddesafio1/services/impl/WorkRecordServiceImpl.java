package br.com.thallysprojetos.backenddesafio1.services.impl;

import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordResponseDTO;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsAlreadyExistException;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsNotFoundException;
import br.com.thallysprojetos.backenddesafio1.mappers.WorkRecordMappers;
import br.com.thallysprojetos.backenddesafio1.messaging.WorkRecordEventPublisher;
import br.com.thallysprojetos.backenddesafio1.models.WorkRecord;
import br.com.thallysprojetos.backenddesafio1.repositories.WorkRecordRepository;
import br.com.thallysprojetos.backenddesafio1.services.WorkRecordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WorkRecordServiceImpl implements WorkRecordService {

    private final WorkRecordRepository workRecordRepository;
    private final WorkRecordMappers mappers;
    private final WorkRecordEventPublisher eventPublisher;

    public WorkRecordDTO checkIn(Long employeeId) {
        WorkRecord open = workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId);
        if (open != null) {
            throw new WorkRecordsAlreadyExistException("Employee " + employeeId + " already checked-in and did not check-out yet.");
        }

        WorkRecord record = new WorkRecord();
        record.setEmployeeId(employeeId);
        record.setCheckInTime(LocalDateTime.now());
        record = workRecordRepository.save(record);

        // Publica evento assíncrono de check-in
        eventPublisher.publishCheckInEvent(employeeId, record.getId());

        return mappers.toDTO(record);
    }

    public WorkRecordDTO checkOut(Long employeeId) {
        WorkRecord open = workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId);
        if (open == null) {
            throw new WorkRecordsNotFoundException("No open check-in found for employee " + employeeId);
        }

        LocalDateTime now = LocalDateTime.now();
        open.setCheckOutTime(now);
        Duration dur = Duration.between(open.getCheckInTime(), now);

        long seconds = dur.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        String formatted = String.format("%02d:%02d:%02d", hours, minutes, secs);
        open.setDuration(formatted);
        workRecordRepository.save(open);

        // Publica evento assíncrono de check-out
        eventPublisher.publishCheckOutEvent(employeeId, open.getId(), formatted);

        return mappers.toDTO(open);
    }

    public Page<WorkRecordDTO> list(Integer page, Integer size, String nameFilter, LocalDateTime date) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 20);
        Page<WorkRecord> records = workRecordRepository.findAll(pageable);

        List<WorkRecordDTO> dtos = records.stream().map(mappers::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, records.getTotalElements());
    }

    public List<WorkRecordResponseDTO> findAll() {
        List<WorkRecordResponseDTO> workRecords = workRecordRepository.findAllWorkRecordsWithEmployeeNames();
        if (workRecords.isEmpty()) {
            throw new WorkRecordsNotFoundException("No work records found.");
        }

        return workRecords;
    }

}
