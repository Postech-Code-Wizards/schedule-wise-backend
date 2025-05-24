package com.scheduling.wise.gateway.messaging.publisher;

import com.scheduling.wise.gateway.messaging.constants.QueueConstants;
import com.scheduling.wise.gateway.messaging.message.PreviousConsultationsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryProducerService {

    private final RabbitTemplate rabbitTemplate;

    public void sendNewHistory(PreviousConsultationsRequest message) {
        rabbitTemplate.convertAndSend(QueueConstants.HISTORY_NEW_QUEUE, message);
    }

    public void sendUpdatedHistory(PreviousConsultationsRequest message) {
        rabbitTemplate.convertAndSend(QueueConstants.HISTORY_UPDATE_QUEUE, message);
    }
}