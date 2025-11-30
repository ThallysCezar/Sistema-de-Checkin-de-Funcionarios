package br.com.thallysprojetos.backenddesafio1.factories;

import br.com.thallysprojetos.backenddesafio1.models.AuthCredential;

public class AuthCredentialFactory {

    public static AuthCredential createEmployeeCredentials() {
        return createAuthCredential(1L, 1L, "john.doe@example.com", "password123", "EMPLOYEE");
    }

    public static AuthCredential createManagerCredentials() {
        return createAuthCredential(2L, 2L, "manager@example.com", "password123", "MANAGER");
    }

    public static AuthCredential createAdminCredentials() {
        return createAuthCredential(3L, 3L, "admin@example.com", "password123", "ADMIN");
    }

    public static AuthCredential createAuthCredential(Long id, Long employeeId, String email, String password, String role) {
        AuthCredential credential = new AuthCredential();
        credential.setId(id);
        credential.setEmployeeId(employeeId);
        credential.setEmail(email);
        credential.setPassword(password);
        credential.setRole(role);
        return credential;
    }

    public static AuthCredential createAuthCredentialWithoutId(Long employeeId, String email, String password, String role) {
        AuthCredential credential = new AuthCredential();
        credential.setEmployeeId(employeeId);
        credential.setEmail(email);
        credential.setPassword(password);
        credential.setRole(role);
        return credential;
    }

}