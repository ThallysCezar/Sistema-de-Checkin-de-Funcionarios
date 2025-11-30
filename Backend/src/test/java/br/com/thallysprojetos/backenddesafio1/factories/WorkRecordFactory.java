package br.com.thallysprojetos.backenddesafio1.factories;

import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordDTO;
import br.com.thallysprojetos.backenddesafio1.models.WorkRecord;

import java.time.LocalDateTime;

public class WorkRecordFactory {

    public static WorkRecord createWorkRecord() {
        return createWorkRecord(1L, 1L, LocalDateTime.now(), null, null);
    }

    public static WorkRecord createWorkRecord(Long id, Long employeeId, LocalDateTime checkInTime, LocalDateTime checkOutTime, String duration) {
        WorkRecord workRecord = new WorkRecord();
        workRecord.setId(id);
        workRecord.setEmployeeId(employeeId);
        workRecord.setCheckInTime(checkInTime);
        workRecord.setCheckOutTime(checkOutTime);
        workRecord.setDuration(duration);
        return workRecord;
    }

    public static WorkRecord createOpenWorkRecord(Long employeeId) {
        return createWorkRecord(null, employeeId, LocalDateTime.now(), null, null);
    }

    public static WorkRecord createClosedWorkRecord(Long employeeId) {
        LocalDateTime checkIn = LocalDateTime.of(2025, 11, 30, 8, 0, 0);
        LocalDateTime checkOut = LocalDateTime.of(2025, 11, 30, 17, 0, 0);
        return createWorkRecord(1L, employeeId, checkIn, checkOut, "09:00:00");
    }

    public static WorkRecord createWorkRecordWithSpecificTimes(Long employeeId, LocalDateTime checkIn, LocalDateTime checkOut) {
        return createWorkRecord(1L, employeeId, checkIn, checkOut, null);
    }

    public static WorkRecordDTO createWorkRecordDTO() {
        return createWorkRecordDTO(1L, 1L, LocalDateTime.now(), null, null);
    }

    public static WorkRecordDTO createWorkRecordDTO(Long id, Long employeeId, LocalDateTime checkInTime, LocalDateTime checkOutTime, String duration) {
        WorkRecordDTO dto = new WorkRecordDTO();
        dto.setId(id);
        dto.setEmployeeId(employeeId);
        dto.setCheckInTime(checkInTime);
        dto.setCheckOutTime(checkOutTime);
        dto.setDuration(duration);
        return dto;
    }

}