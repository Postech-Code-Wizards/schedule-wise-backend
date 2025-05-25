package com.scheduling.wise.gateway.messaging.message;

import com.scheduling.wise.gateway.messaging.message.enums.DeliveryMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class StreamMessage {
    private String notificationId;
    private DeliveryMethod deliveryMethod;
    private String recipient;
    private String message;
}