package br.com.thallysprojetos.backenddesafio1.repositories;

import br.com.thallysprojetos.backenddesafio1.models.WorkRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long> {

    WorkRecord findByEmployeeIdAndCheckOutTimeIsNull(Long employeeId);

}