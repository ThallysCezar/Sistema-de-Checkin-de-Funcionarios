package br.com.thallysprojetos.backenddesafio1.factories;

import br.com.thallysprojetos.backenddesafio1.dtos.EmployeeDTO;
import br.com.thallysprojetos.backenddesafio1.models.Employee;

public class EmployeeFactory {

    public static Employee createEmployee() {
        return createEmployee(1L, "John Doe", "john.doe@example.com");
    }

    public static Employee createEmployee(Long id, String name, String email) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        return employee;
    }

    public static Employee createEmployeeWithoutId(String name, String email) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        return employee;
    }

    public static EmployeeDTO createEmployeeDTO() {
        return createEmployeeDTO(1L, "John Doe", "john.doe@example.com");
    }

    public static EmployeeDTO createEmployeeDTO(Long id, String name, String email) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        return dto;
    }

    public static EmployeeDTO createEmployeeDTOWithoutId(String name, String email) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(name);
        dto.setEmail(email);
        return dto;
    }

}