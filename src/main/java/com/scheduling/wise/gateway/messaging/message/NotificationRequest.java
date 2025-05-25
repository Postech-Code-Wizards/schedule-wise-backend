package com.scheduling.wise.gateway.messaging.message;

import com.scheduling.wise.gateway.messaging.message.enums.DeliveryMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class NotificationRequest {
    private String templateName;
    private DeliveryMethod deliveryMethod;
    private Long patientId;
    private String recipient;
    private List<KeyValueRequest> additionalInfo;
}