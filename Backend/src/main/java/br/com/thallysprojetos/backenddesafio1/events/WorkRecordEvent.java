package br.com.thallysprojetos.backenddesafio1.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkRecordEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long employeeId;
    private Long workRecordId;
    private EventType eventType;
    private LocalDateTime timestamp;
    private String duration; // Apenas para CHECK_OUT

    public enum EventType {
        CHECK_IN,
        CHECK_OUT
    }

    public static WorkRecordEvent checkIn(Long employeeId, Long workRecordId) {
        return new WorkRecordEvent(employeeId, workRecordId, EventType.CHECK_IN, LocalDateTime.now(), null);
    }

    public static WorkRecordEvent checkOut(Long employeeId, Long workRecordId, String duration) {
        return new WorkRecordEvent(employeeId, workRecordId, EventType.CHECK_OUT, LocalDateTime.now(), duration);
    }
}
