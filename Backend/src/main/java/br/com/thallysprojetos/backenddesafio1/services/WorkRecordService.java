package br.com.thallysprojetos.backenddesafio1.services;

import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordDTO;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.ModelAlreadyExistException;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.ModelNotFoundException;
import br.com.thallysprojetos.backenddesafio1.mappers.WorkRecordMappers;
import br.com.thallysprojetos.backenddesafio1.models.WorkRecord;
import br.com.thallysprojetos.backenddesafio1.repositories.WorkRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkRecordService {

    private final WorkRecordRepository workRecordRepository;
    private final WorkRecordMappers mappers;

    public WorkRecordDTO checkIn(Long employeeId) {
        WorkRecord open = workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId);
        if (open != null) {
            throw new ModelAlreadyExistException("Employee " + employeeId + " already checked-in and did not check-out yet.");
        }

        WorkRecord record = new WorkRecord();
        record.setEmployeeId(employeeId);
        record.setCheckInTime(LocalDateTime.now());
        record = workRecordRepository.save(record);

        return mappers.toDTO(record);
    }

    public WorkRecordDTO checkOut(Long employeeId) {
        WorkRecord open = workRecordRepository.findByEmployeeIdAndCheckOutTimeIsNull(employeeId);
        if (open == null) {
            throw new ModelNotFoundException("No open check-in found for employee " + employeeId);
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

        return mappers.toDTO(open);
    }

    public Page<WorkRecordDTO> list(Integer page, Integer size, String nameFilter, LocalDateTime date) {
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 20);
        Page<WorkRecord> records = workRecordRepository.findAll(pageable);

        List<WorkRecordDTO> dtos = records.stream().map(mappers::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, records.getTotalElements());
    }

    public List<WorkRecordDTO> findAll() {
        List<WorkRecord> workeRecords = workRecordRepository.findAll();
        if (workeRecords.isEmpty()) {
            throw new NoSuchElementException("No workdRecords found.");
        }

        return mappers.toListDTO(workeRecords);
    }

}