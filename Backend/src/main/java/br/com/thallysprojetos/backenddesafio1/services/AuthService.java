package br.com.thallysprojetos.backenddesafio1.services;

import br.com.thallysprojetos.backenddesafio1.dtos.EmployeeDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginResponseDTO;

import java.util.List;

public interface AuthService {

    List<EmployeeDTO> findAll();

    EmployeeDTO findById(Long id);

    EmployeeDTO save(EmployeeDTO employeeDTO);

    LoginResponseDTO login(LoginDTO loginDTO);

}