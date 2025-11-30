package br.com.thallysprojetos.backenddesafio1.services;

import br.com.thallysprojetos.backenddesafio1.dtos.EmployeeDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginResponseDTO;
import br.com.thallysprojetos.backenddesafio1.exceptions.employeers.EmployeersException;
import br.com.thallysprojetos.backenddesafio1.exceptions.employeers.EmployeersNotFoundException;
import br.com.thallysprojetos.backenddesafio1.factories.AuthCredentialFactory;
import br.com.thallysprojetos.backenddesafio1.factories.EmployeeFactory;
import br.com.thallysprojetos.backenddesafio1.factories.LoginFactory;
import br.com.thallysprojetos.backenddesafio1.mappers.EmployeeMappers;
import br.com.thallysprojetos.backenddesafio1.models.AuthCredential;
import br.com.thallysprojetos.backenddesafio1.models.Employee;
import br.com.thallysprojetos.backenddesafio1.repositories.AuthCredentialsRepository;
import br.com.thallysprojetos.backenddesafio1.repositories.EmployeesRepository;
import br.com.thallysprojetos.backenddesafio1.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthServiceImpl Unit Tests")
class AuthServiceImplTest {

    @Mock
    private EmployeesRepository employeesRepository;

    @Mock
    private AuthCredentialsRepository authCredentialsRepository;

    @Mock
    private EmployeeMappers modelMapper;

    @InjectMocks
    private AuthServiceImpl authService;

    private Employee employee;
    private EmployeeDTO employeeDTO;
    private AuthCredential authCredentials;
    private LoginDTO loginDTO;
    private Long employeeId;

    @BeforeEach
    void setUp() {
        employeeId = 1L;
        employee = EmployeeFactory.createEmployee();
        employeeDTO = EmployeeFactory.createEmployeeDTO();
        authCredentials = AuthCredentialFactory.createEmployeeCredentials();
        loginDTO = LoginFactory.createValidLoginDTO();
    }

    @Test
    @DisplayName("Should successfully login with valid credentials")
    void testLogin_Success_WithValidCredentials() {
        when(authCredentialsRepository.findByEmail(loginDTO.getLogin()))
                .thenReturn(Optional.of(authCredentials));
        when(employeesRepository.findById(authCredentials.getEmployeeId()))
                .thenReturn(Optional.of(employee));
        when(modelMapper.toDTO(employee)).thenReturn(employeeDTO);

        LoginResponseDTO result = authService.login(loginDTO);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(employeeDTO.getName());
        assertThat(result.getEmail()).isEqualTo(employeeDTO.getEmail());
        verify(authCredentialsRepository).findByEmail(loginDTO.getLogin());
        verify(employeesRepository).findById(authCredentials.getEmployeeId());
    }

    @Test
    @DisplayName("Should throw ResponseStatusException when user not found")
    void testLogin_ThrowsException_WhenUserNotFound() {
        when(authCredentialsRepository.findByEmail(loginDTO.getLogin()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.login(loginDTO))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("User not found");

        verify(authCredentialsRepository).findByEmail(loginDTO.getLogin());
        verify(employeesRepository, never()).findById(anyLong());
    }

    @Test
    @DisplayName("Should throw ResponseStatusException when password is invalid")
    void testLogin_ThrowsException_WhenInvalidPassword() {

        LoginDTO wrongPasswordLogin = LoginFactory.createInvalidLoginDTO();
        when(authCredentialsRepository.findByEmail(wrongPasswordLogin.getLogin()))
                .thenReturn(Optional.of(authCredentials));

        assertThatThrownBy(() -> authService.login(wrongPasswordLogin))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Invalid credentials");

        verify(authCredentialsRepository).findByEmail(wrongPasswordLogin.getLogin());
        verify(employeesRepository, never()).findById(anyLong());
    }

    @Test
    @DisplayName("Should throw ResponseStatusException when employee not found after authentication")
    void testLogin_ThrowsException_WhenEmployeeNotFound() {

        when(authCredentialsRepository.findByEmail(loginDTO.getLogin()))
                .thenReturn(Optional.of(authCredentials));
        when(employeesRepository.findById(authCredentials.getEmployeeId()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.login(loginDTO))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("User not found");

        verify(authCredentialsRepository).findByEmail(loginDTO.getLogin());
        verify(employeesRepository).findById(authCredentials.getEmployeeId());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when login with null credentials")
    void testLogin_ThrowsException_WithNullCredentials() {
        assertThatThrownBy(() -> authService.login(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Login and password are required");

        verify(authCredentialsRepository, never()).findByEmail(anyString());
    }

    @Test
    @DisplayName("Should throw ResponseStatusException when login with empty username")
    void testLogin_ThrowsException_WithEmptyUsername() {

        LoginDTO emptyUsernameLogin = LoginFactory.createEmptyUsernameLoginDTO();
        when(authCredentialsRepository.findByEmail("")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.login(emptyUsernameLogin))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("User not found");

        verify(authCredentialsRepository).findByEmail("");
    }

    @Test
    @DisplayName("Should return all employees successfully")
    void testFindAll_Success() {

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(EmployeeFactory.createEmployee(2L, "Jane Smith", "jane.smith@example.com"));

        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        employeeDTOs.add(employeeDTO);
        employeeDTOs.add(EmployeeFactory.createEmployeeDTO(2L, "Jane Smith", "jane.smith@example.com"));

        when(employeesRepository.findAll()).thenReturn(employees);
        when(modelMapper.toListDTO(employees)).thenReturn(employeeDTOs);

        List<EmployeeDTO> result = authService.findAll();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("John Doe");
        assertThat(result.get(1).getName()).isEqualTo("Jane Smith");
        verify(employeesRepository).findAll();
        verify(modelMapper).toListDTO(employees);
    }

    @Test
    @DisplayName("Should throw EmployeersNotFoundException when no employees found")
    void testFindAll_ThrowsException_WhenNoEmployeesFound() {

        when(employeesRepository.findAll()).thenReturn(new ArrayList<>());

        assertThatThrownBy(() -> authService.findAll())
                .isInstanceOf(EmployeersNotFoundException.class)
                .hasMessageContaining("No employees found");

        verify(employeesRepository).findAll();
    }

    @Test
    @DisplayName("Should return employee by ID successfully")
    void testFindById_Success() {

        when(employeesRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(modelMapper.toDTO(employee)).thenReturn(employeeDTO);

        EmployeeDTO result = authService.findById(employeeId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(employeeId);
        assertThat(result.getName()).isEqualTo("John Doe");
        verify(employeesRepository).findById(employeeId);
        verify(modelMapper).toDTO(employee);
    }

    @Test
    @DisplayName("Should throw EmployeersNotFoundException when employee not found by ID")
    void testFindById_ThrowsException_WhenNotFound() {

        when(employeesRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.findById(employeeId))
                .isInstanceOf(EmployeersNotFoundException.class);

        verify(employeesRepository).findById(employeeId);
        verify(modelMapper, never()).toDTO(any(Employee.class));
    }

    @Test
    @DisplayName("Should save employee successfully")
    void testSave_Success() {

        when(modelMapper.toEntity(employeeDTO)).thenReturn(employee);
        when(employeesRepository.save(employee)).thenReturn(employee);
        when(modelMapper.toDTO(employee)).thenReturn(employeeDTO);

        EmployeeDTO result = authService.save(employeeDTO);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(employeeDTO.getName());
        assertThat(result.getEmail()).isEqualTo(employeeDTO.getEmail());
        verify(modelMapper).toEntity(employeeDTO);
        verify(employeesRepository).save(employee);
        verify(modelMapper).toDTO(employee);
    }

    @Test
    @DisplayName("Should throw EmployeersException when save fails")
    void testSave_ThrowsException_WhenSaveFails() {

        when(modelMapper.toEntity(employeeDTO)).thenReturn(employee);
        when(employeesRepository.save(employee))
                .thenThrow(new RuntimeException("Database error"));

        assertThatThrownBy(() -> authService.save(employeeDTO))
                .isInstanceOf(EmployeersException.class);

        verify(modelMapper).toEntity(employeeDTO);
        verify(employeesRepository).save(employee);
    }

    @Test
    @DisplayName("Should successfully login as manager")
    void testLogin_Success_AsManager() {

        AuthCredential managerCredentials = AuthCredentialFactory.createManagerCredentials();
        LoginDTO managerLogin = LoginFactory.createValidLoginDTO("manager@example.com", "password123");

        when(authCredentialsRepository.findByEmail(managerLogin.getLogin()))
                .thenReturn(Optional.of(managerCredentials));
        when(employeesRepository.findById(managerCredentials.getEmployeeId()))
                .thenReturn(Optional.of(employee));
        when(modelMapper.toDTO(employee)).thenReturn(employeeDTO);

        LoginResponseDTO result = authService.login(managerLogin);

        assertThat(result).isNotNull();
        assertThat(result.getRole()).isEqualTo("MANAGER");
        verify(authCredentialsRepository).findByEmail(managerLogin.getLogin());
    }

    @Test
    @DisplayName("Should successfully login as admin")
    void testLogin_Success_AsAdmin() {

        AuthCredential adminCredentials = AuthCredentialFactory.createAdminCredentials();
        LoginDTO adminLogin = LoginFactory.createValidLoginDTO("admin@example.com", "password123");

        when(authCredentialsRepository.findByEmail(adminLogin.getLogin()))
                .thenReturn(Optional.of(adminCredentials));
        when(employeesRepository.findById(adminCredentials.getEmployeeId()))
                .thenReturn(Optional.of(employee));
        when(modelMapper.toDTO(employee)).thenReturn(employeeDTO);

        LoginResponseDTO result = authService.login(adminLogin);

        assertThat(result).isNotNull();
        assertThat(result.getRole()).isEqualTo("ADMIN");
        verify(authCredentialsRepository).findByEmail(adminLogin.getLogin());
    }

}