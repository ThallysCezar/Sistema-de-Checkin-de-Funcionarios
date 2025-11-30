package br.com.thallysprojetos.backenddesafio1.repositories;

import br.com.thallysprojetos.backenddesafio1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employee, Long> {
}