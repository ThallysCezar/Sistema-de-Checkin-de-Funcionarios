package br.com.thallysprojetos.backenddesafio1.startup;

import br.com.thallysprojetos.backenddesafio1.models.AuthCredential;
import br.com.thallysprojetos.backenddesafio1.models.Employee;
import br.com.thallysprojetos.backenddesafio1.repositories.AuthCredentialsRepository;
import br.com.thallysprojetos.backenddesafio1.repositories.EmployeesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class AuthCredentialsSeeder implements CommandLineRunner {

    private final EmployeesRepository employeesRepository;
    private final AuthCredentialsRepository authCredentialsRepository;

    public AuthCredentialsSeeder(EmployeesRepository employeesRepository, AuthCredentialsRepository authCredentialsRepository) {
        this.employeesRepository = employeesRepository;
        this.authCredentialsRepository = authCredentialsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedCredential("admin@example.com", "admin", "admin");
        seedCredential("manager@example.com", "manager", "manager");
        seedCredential("employee@example.com", "password", "employee");
    }

    private void seedCredential(String email, String password, String role) {
        // Find any credentials for the email, handle duplicates and ensure correct mapping
        List<AuthCredential> existing = authCredentialsRepository.findAllByEmail(email);
        Optional<Employee> employeeOptional = employeesRepository.findByEmail(email);

        // If no linked employee exists, remove any broken credentials and do nothing
        if (employeeOptional.isEmpty()) {
            if (!existing.isEmpty()) {
                existing.forEach(authCredentialsRepository::delete);
            }
            return;
        }

        Employee emp = employeeOptional.get();

        if (existing.isEmpty()) {
            // create credential
            AuthCredential cred = new AuthCredential();
            cred.setEmployeeId(emp.getId());
            cred.setEmail(email);
            cred.setPassword(password);
            cred.setRole(role);
            authCredentialsRepository.save(cred);
            return;
        }

        // If multiple exist, keep first and delete others
        AuthCredential primary = existing.get(0);
        for (int i = 1; i < existing.size(); i++) {
            authCredentialsRepository.delete(existing.get(i));
        }

        // Update primary if mismatched
        boolean changed = false;
        if (!emp.getId().equals(primary.getEmployeeId())) {
            primary.setEmployeeId(emp.getId());
            changed = true;
        }
        if (!role.equals(primary.getRole())) {
            primary.setRole(role);
            changed = true;
        }
        if (!password.equals(primary.getPassword())) {
            primary.setPassword(password);
            changed = true;
        }
        if (changed) {
            authCredentialsRepository.save(primary);
        }
    }
}
