package com.scheduling.wise.gateway.messaging.publisher;

import com.scheduling.wise.gateway.messaging.message.StreamMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishConsultationCreated(StreamMessage message) {
        rabbitTemplate.convertAndSend("notification_exchange", "email.queue", message);
    }
}
