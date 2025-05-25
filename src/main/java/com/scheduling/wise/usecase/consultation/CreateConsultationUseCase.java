package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.gateway.messaging.message.NotificationRequest;
import com.scheduling.wise.gateway.messaging.message.enums.DeliveryMethod;
import com.scheduling.wise.gateway.messaging.publisher.NotificationProducerService;
import com.scheduling.wise.usecase.patient.GetPatientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    private final GetPatientUseCase getPatientUseCase;

    private final NotificationProducerService notificationProducerService;

    public void execute(Consultation consultation) {
        consultation.setStatus(Status.SCHEDULED);
        var consultationSaved = consultationGateway.save(consultation);

        sendNotification(consultationSaved);
    }

    private void sendNotification(Consultation consultation) {
        var message = NotificationRequest.builder()
                .templateName("CONSULTATION_CREATED")
                .deliveryMethod(DeliveryMethod.WHATSAPP)
                .patientId(consultation.getPatient().getId())
                .recipient(consultation.getPatient().getUser().getEmail()).build();

        notificationProducerService.sendNotification(message);
    }
}
