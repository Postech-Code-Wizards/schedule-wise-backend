package com.scheduling.wise.gateway.messaging.publisher;

import com.scheduling.wise.gateway.messaging.constants.QueueConstants;
import com.scheduling.wise.gateway.messaging.message.StreamMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducerService {

    private final RabbitTemplate rabbitTemplate;

    public void sendEmailNotification(StreamMessage message) {
        rabbitTemplate.convertAndSend(QueueConstants.NOTIFICATION_EMAIL_QUEUE, message);
    }

    public void sendSmsNotification(StreamMessage message) {
        rabbitTemplate.convertAndSend(QueueConstants.NOTIFICATION_SMS_QUEUE, message);
    }

    public void sendWhatsappNotification(StreamMessage message) {
        rabbitTemplate.convertAndSend(QueueConstants.NOTIFICATION_WHATSAPP_QUEUE, message);
    }
}
