package br.com.thallysprojetos.backenddesafio1.messaging;

import br.com.thallysprojetos.backenddesafio1.configs.RabbitMQConfig;
import br.com.thallysprojetos.backenddesafio1.events.WorkRecordEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkRecordEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishCheckInEvent(Long employeeId, Long workRecordId) {
        try {
            WorkRecordEvent event = WorkRecordEvent.checkIn(employeeId, workRecordId);
            rabbitTemplate.convertAndSend(RabbitMQConfig.WORK_RECORD_QUEUE, event);
            log.info("Published CHECK_IN event for employee {} - workRecord {}", employeeId, workRecordId);
        } catch (Exception e) {
            log.error("Error publishing CHECK_IN event for employee {}: {}", employeeId, e.getMessage());
        }
    }

    public void publishCheckOutEvent(Long employeeId, Long workRecordId, String duration) {
        try {
            WorkRecordEvent event = WorkRecordEvent.checkOut(employeeId, workRecordId, duration);
            rabbitTemplate.convertAndSend(RabbitMQConfig.WORK_RECORD_QUEUE, event);
            log.info("Published CHECK_OUT event for employee {} - workRecord {} - duration: {}", 
                     employeeId, workRecordId, duration);
        } catch (Exception e) {
            log.error("Error publishing CHECK_OUT event for employee {}: {}", employeeId, e.getMessage());
        }
    }

}