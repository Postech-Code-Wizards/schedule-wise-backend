package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.gateway.messaging.message.StreamMessage;
import com.scheduling.wise.gateway.messaging.message.enums.DeliveryMethod;
import com.scheduling.wise.gateway.messaging.publisher.NotificationProducerService;
import com.scheduling.wise.usecase.patient.GetPatientUseCase;
import com.scheduling.wise.usecase.phone.GetPhoneUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    private final GetPatientUseCase getPatientUseCase;
    private final GetPhoneUseCase getPhoneUseCase;

    private final NotificationProducerService notificationProducerService;

    public void execute(Consultation consultation) {
        consultation.setStatus(Status.SCHEDULED);
        consultationGateway.save(consultation);

        Patient patient = getPatientUseCase.execute(consultation.getPatient().getId());
        Phone phone = getPhoneUseCase.execute(patient.getPhone().getId());
        sendWhatsappNotification(consultation, phone);
    }

    private void sendWhatsappNotification(Consultation consultation, Phone phone) {
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
