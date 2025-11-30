package br.com.thallysprojetos.backenddesafio1.controllers;

import br.com.thallysprojetos.backenddesafio1.dtos.ErrorResponseDTO;
import br.com.thallysprojetos.backenddesafio1.dtos.WorkRecordDTO;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsAlreadyExistException;
import br.com.thallysprojetos.backenddesafio1.exceptions.workRecord.WorkRecordsNotFoundException;
import br.com.thallysprojetos.backenddesafio1.services.WorkRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Work", description = "API for Work Record management")
public class WorkController {

    private final WorkRecordService service;

    @Operation(summary = "Checkin Employeee")
    @PostMapping("/checkin")
    public ResponseEntity<WorkRecordDTO> checkIn(@RequestBody WorkRecordDTO dto) {
        WorkRecordDTO saved = service.checkIn(dto.getEmployeeId());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Checkout Employeee")
    @PostMapping("/checkout")
    public ResponseEntity<WorkRecordDTO> checkOut(@RequestBody WorkRecordDTO dto) {
        WorkRecordDTO updated = service.checkOut(dto.getEmployeeId());
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "List all Work Records with pagination")
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

    @Operation(summary = "List all Work Records")
    @GetMapping
    public ResponseEntity<List<WorkRecordDTO>> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

}