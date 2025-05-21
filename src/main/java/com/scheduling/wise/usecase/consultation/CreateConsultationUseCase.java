package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.gateway.messaging.message.StreamMessage;
import com.scheduling.wise.gateway.messaging.message.enums.DeliveryMethod;
import com.scheduling.wise.gateway.messaging.publisher.NotificationProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    private final NotificationProducerService notificationProducerService;

    public void execute(Consultation consultation) {
        consultation.setStatus(Status.SCHEDULED);
        consultationGateway.save(consultation);

        sendWhatsappNotification(consultation);
    }

    private void sendWhatsappNotification(Consultation consultation) {
        var phone = consultation.getPatient().getPhone();
        var fullPhone = "+" + phone.getAreaCode() + phone.getPhoneNumber();

        var message = StreamMessage.builder()
                .notificationId(String.valueOf(consultation.getId()))
                .deliveryMethod(DeliveryMethod.WHATSAPP)
                .recipient(fullPhone)
                .message("Sua consulta foi agendada com sucesso para " + consultation.getScheduledAt())
                .build();

        notificationProducerService.sendWhatsappNotification(message);
    }
}
