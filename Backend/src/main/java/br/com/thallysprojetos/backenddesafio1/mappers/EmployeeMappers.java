package br.com.thallysprojetos.backenddesafio1.mappers;

import br.com.thallysprojetos.backenddesafio1.dtos.EmployeeDTO;
import br.com.thallysprojetos.backenddesafio1.models.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMappers {
    private final ModelMapper modelMapper;

    public EmployeeMappers(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO toDTO(Employee entity) {
        return modelMapper.map(entity, EmployeeDTO.class);
    }

    public Employee toEntity(EmployeeDTO dto) {
        return modelMapper.map(dto, Employee.class);
    }

    public List<EmployeeDTO> toListDTO(List<Employee> employeeList) {
        return employeeList.stream()
                .map(this::toDTO).toList();
    }

    public List<Employee> toList(List<EmployeeDTO> dtosList) {
        return dtosList.stream()
                .map(this::toEntity).toList();
    }

}