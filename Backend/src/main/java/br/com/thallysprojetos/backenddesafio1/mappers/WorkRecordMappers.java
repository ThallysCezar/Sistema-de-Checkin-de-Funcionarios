package br.com.thallysprojetos.backenddesafio1.mappers;

import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordDTO;
import br.com.thallysprojetos.backenddesafio1.models.WorkRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkRecordMappers {
    private final ModelMapper modelMapper;

    public WorkRecordMappers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public WorkRecordDTO toDTO(WorkRecord entity) {
        return modelMapper.map(entity, WorkRecordDTO.class);
    }

    public WorkRecord toEntity(WorkRecordDTO dto) {
        WorkRecord entity = modelMapper.map(dto, WorkRecord.class);
        return entity;
    }

    public List<WorkRecordDTO> toListDTO(List<WorkRecord> employeeList) {
        return employeeList.stream()
                .map(this::toDTO).toList();
    }

    public List<WorkRecord> toList(List<WorkRecordDTO> dtosList) {
        return dtosList.stream()
                .map(this::toEntity).toList();
    }

}