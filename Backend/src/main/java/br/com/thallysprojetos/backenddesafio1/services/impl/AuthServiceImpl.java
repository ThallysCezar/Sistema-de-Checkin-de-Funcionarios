package br.com.thallysprojetos.backenddesafio1.services.impl;

import br.com.thallysprojetos.backenddesafio1.dtos.EmployeeDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginResponseDTO;
import br.com.thallysprojetos.backenddesafio1.exceptions.employeers.EmployeersException;
import br.com.thallysprojetos.backenddesafio1.exceptions.employeers.EmployeersNotFoundException;
import br.com.thallysprojetos.backenddesafio1.mappers.EmployeeMappers;
import br.com.thallysprojetos.backenddesafio1.models.Employee;
import br.com.thallysprojetos.backenddesafio1.repositories.AuthCredentialsRepository;
import br.com.thallysprojetos.backenddesafio1.repositories.EmployeesRepository;
import br.com.thallysprojetos.backenddesafio1.services.AuthService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeesRepository employeesRepository;
    private final EmployeeMappers modelMapper;
    private final AuthCredentialsRepository authCredentialsRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeesRepository.findAll();
        if (employees.isEmpty()) {
            throw new EmployeersNotFoundException("No employees found.");
        }

        return modelMapper.toListDTO(employees);
    }

    public EmployeeDTO findById(Long id) {
        return employeesRepository.findById(id)
                .map(modelMapper::toDTO)
                .orElseThrow(EmployeersNotFoundException::new);
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        try {
            return modelMapper.toDTO(employeesRepository.save(modelMapper.toEntity(employeeDTO)));
        } catch (Exception exModel) {
            throw new EmployeersException();
        }
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        if (loginDTO == null || loginDTO.getLogin() == null || loginDTO.getPassword() == null) {
            throw new IllegalArgumentException("Login and password are required");
        }

        log.debug("Login attempt for email: {}", loginDTO.getLogin());
        return authCredentialsRepository.findByEmail(loginDTO.getLogin())
                .map(cred -> {
                    if (cred.getPassword() == null || !cred.getPassword().equals(loginDTO.getPassword())) {
                        log.debug("Invalid password for email: {}", loginDTO.getLogin());
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
                    }
                    return employeesRepository.findById(cred.getEmployeeId())
                            .map(employee -> {
                                EmployeeDTO dto = modelMapper.toDTO(employee);
                                LoginResponseDTO response = new LoginResponseDTO(dto.getId(), dto.getName(), dto.getEmail(), cred.getRole());
                                log.debug("Login successful for email: {}, id: {}, role: {}", cred.getEmail(), response.getId(), response.getRole());
                                return response;
                            })
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

}
