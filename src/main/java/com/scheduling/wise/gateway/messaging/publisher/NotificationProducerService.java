package com.scheduling.wise.gateway.messaging.publisher;

import com.scheduling.wise.gateway.messaging.constants.QueueConstants;
import com.scheduling.wise.gateway.messaging.message.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducerService {

    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(NotificationRequest message) {
        rabbitTemplate.convertAndSend(QueueConstants.NOTIFICATION_QUEUE, message);
    }
}
