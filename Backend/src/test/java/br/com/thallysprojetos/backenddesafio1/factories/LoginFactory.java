package br.com.thallysprojetos.backenddesafio1.factories;

import br.com.thallysprojetos.backenddesafio1.dtos.LoginDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginResponseDTO;

public class LoginFactory {

    public static LoginDTO createValidLoginDTO() {
        return createValidLoginDTO("john.doe@example.com", "password123");
    }

    public static LoginDTO createValidLoginDTO(String login, String password) {
        LoginDTO dto = new LoginDTO();
        dto.setLogin(login);
        dto.setPassword(password);
        return dto;
    }

    public static LoginDTO createInvalidLoginDTO() {
        return createValidLoginDTO("invalid@example.com", "wrongpassword");
    }

    public static LoginDTO createEmptyUsernameLoginDTO() {
        LoginDTO dto = new LoginDTO();
        dto.setLogin("");
        dto.setPassword("password123");
        return dto;
    }

    public static LoginDTO createEmptyLoginDTO() {
        return new LoginDTO();
    }

    public static LoginResponseDTO createLoginResponseDTO() {
        return createLoginResponseDTO(1L, "John Doe", "john.doe@example.com", "EMPLOYEE");
    }

    public static LoginResponseDTO createLoginResponseDTO(Long id, String name, String email, String role) {
        return new LoginResponseDTO(id, name, email, role);
    }

    public static LoginResponseDTO createManagerLoginResponseDTO() {
        return createLoginResponseDTO(2L, "Manager", "manager@example.com", "MANAGER");
    }

    public static LoginResponseDTO createAdminLoginResponseDTO() {
        return createLoginResponseDTO(3L, "Admin", "admin@example.com", "ADMIN");
    }

}