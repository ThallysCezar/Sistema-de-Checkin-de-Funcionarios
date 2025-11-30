package br.com.thallysprojetos.backenddesafio1.repositories;

import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordResponseDTO;
import br.com.thallysprojetos.backenddesafio1.models.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {

    WorkRecord findByEmployeeIdAndCheckOutTimeIsNull(Long employeeId);

    @Query("SELECT new br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordResponseDTO(" +
           "w.id, w.employeeId, e.name, w.checkInTime, w.checkOutTime, w.duration) " +
           "FROM WorkRecord w JOIN Employee e ON w.employeeId = e.id " +
           "ORDER BY w.checkInTime DESC")
    List<WorkRecordResponseDTO> findAllWorkRecordsWithEmployeeNames();

}