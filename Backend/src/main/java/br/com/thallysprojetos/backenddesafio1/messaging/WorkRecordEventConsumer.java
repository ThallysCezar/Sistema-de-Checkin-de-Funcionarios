package br.com.thallysprojetos.backenddesafio1.messaging;

import br.com.thallysprojetos.backenddesafio1.configs.RabbitMQConfig;
import br.com.thallysprojetos.backenddesafio1.events.WorkRecordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkRecordEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.WORK_RECORD_QUEUE)
    public void handleWorkRecordEvent(WorkRecordEvent event) {
        log.info("=== Processing Work Record Event ===");
        log.info("Event Type: {}", event.getEventType());
        log.info("Employee ID: {}", event.getEmployeeId());
        log.info("Work Record ID: {}", event.getWorkRecordId());
        log.info("Timestamp: {}", event.getTimestamp());

        if (event.getEventType() == WorkRecordEvent.EventType.CHECK_OUT) {
            log.info("Duration: {}", event.getDuration());
            // Aqui você pode adicionar lógicas como:
            // - Enviar notificação por email
            // - Gerar relatório de horas trabalhadas
            // - Atualizar dashboard em tempo real
            // - Calcular horas extras
            // - Enviar para sistema de folha de pagamento
        }

        log.info("=== Event Processed Successfully ===\n");
    }
}
