package br.com.thallysprojetos.backenddesafio1.controllers;

import br.com.thallysprojetos.backenddesafio1.dtos.EmployeeDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.ErrorResponseDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordDTO;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.ModelAlreadyExistException;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.ModelNotFoundException;
import br.com.thallysprojetos.backenddesafio1.services.WorkRecordService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/work")
@AllArgsConstructor
public class WorkController {

    private final WorkRecordService service;

    @PostMapping("/checkin")
    public ResponseEntity<?> checkIn(@RequestBody WorkRecordDTO dto) {
        try {
            WorkRecordDTO saved = service.checkIn(dto.getEmployeeId());
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (ModelAlreadyExistException ex) {
            ErrorResponseDTO error = new ErrorResponseDTO(ex.getReason());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut(@RequestBody WorkRecordDTO dto) {
        try {
            WorkRecordDTO updated = service.checkOut(dto.getEmployeeId());
            return ResponseEntity.ok(updated);
        } catch (ModelNotFoundException ex) {
            ErrorResponseDTO error = new ErrorResponseDTO(ex.getReason());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<Page<WorkRecordDTO>> list(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
    ) {
        Page<WorkRecordDTO> result = service.list(page, size, name, date);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<WorkRecordDTO>> listAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

}