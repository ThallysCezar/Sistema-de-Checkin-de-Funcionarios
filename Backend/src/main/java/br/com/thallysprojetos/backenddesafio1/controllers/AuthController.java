package br.com.thallysprojetos.backenddesafio1.controllers;

import br.com.thallysprojetos.backenddesafio1.dtos.EmployeeDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginResponseDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.LoginDTO;
import br.com.thallysprojetos.backenddesafio1.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO login) {
        LoginResponseDTO dto = service.login(login);
        return ResponseEntity.ok(dto);
    }

    // removed /auth/credentials endpoint (dev-only) as requested

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> listAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO dto) {
        EmployeeDTO saved = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}