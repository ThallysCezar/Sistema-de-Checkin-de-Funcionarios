package br.com.thallysprojetos.backenddesafio1.services;

import br.com.thallysprojetos.backenddesafio1.dtos.EmployeeDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginResponseDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginDTO;
import br.com.thallysprojetos.backenddesafio1.mappers.EmployeeMappers;
import br.com.thallysprojetos.backenddesafio1.models.Employee;
import br.com.thallysprojetos.backenddesafio1.repositories.EmployeesRepository;
import br.com.thallysprojetos.backenddesafio1.repositories.AuthCredentialsRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService {

    private final EmployeesRepository employeesRepository;
    private final EmployeeMappers modelMapper;
    private final AuthCredentialsRepository authCredentialsRepository;

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeesRepository.findAll();
        if (employees.isEmpty()) {
            throw new NoSuchElementException("No employees found.");
        }

        return modelMapper.toListDTO(employees);
    }

    public EmployeeDTO findById(Long id) {
        return employeesRepository.findById(id)
                .map(modelMapper::toDTO)
                .orElseThrow(NoSuchElementException::new);
    }

    

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        try {
            return modelMapper.toDTO(employeesRepository.save(modelMapper.toEntity(employeeDTO)));
        } catch (Exception exModel) {
            throw new RuntimeException(exModel);
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