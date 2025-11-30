package br.com.thallysprojetos.backenddesafio1.services;

import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkRecordService {

    WorkRecordDTO checkIn(Long employeeId);

    WorkRecordDTO checkOut(Long employeeId);

    Page<WorkRecordDTO> list(Integer page, Integer size, String nameFilter, LocalDateTime date);

    List<WorkRecordResponseDTO> findAll();

}