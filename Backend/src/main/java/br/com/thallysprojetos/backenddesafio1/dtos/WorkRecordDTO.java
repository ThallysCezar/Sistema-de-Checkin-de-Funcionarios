package br.com.thallysprojetos.backenddesafio1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkRecordDTO {

    private Long id;

    private Long employeeId;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private String duration;

}