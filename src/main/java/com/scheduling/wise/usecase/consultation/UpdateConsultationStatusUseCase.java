package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.gateway.messaging.message.PreviousConsultationsRequest;
import com.scheduling.wise.gateway.messaging.publisher.HistoryProducerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateConsultationStatusUseCase {
    private final ConsultationGateway consultationGateway;
    private final HistoryProducerService historyProducerService;

    public void execute(Long id, Consultation consultation) {
        consultationGateway.updateStatus(id, consultation);
        historyProducerService.sendUpdatedHistory(mapToHistoryMessage(consultation));
    }

    private PreviousConsultationsRequest mapToHistoryMessage(Consultation consultation) {
        Long diagnosticId = null;
        Long symptomId = null;

        if (consultation.getDiagnostics() != null && !consultation.getDiagnostics().isEmpty()) {
            Diagnostic firstDiagnostic = consultation.getDiagnostics().get(0);
            diagnosticId = firstDiagnostic.getId();

            if (firstDiagnostic.getSymptoms() != null && !firstDiagnostic.getSymptoms().isEmpty()) {
                Symptom firstSymptom = firstDiagnostic.getSymptoms().get(0);
                symptomId = firstSymptom.getId();
            }
        }

        return PreviousConsultationsRequest.builder()
                .consultationId(consultation.getId())
                .patientId(consultation.getPatient() != null ? consultation.getPatient().getId() : null)
                .doctorId(consultation.getDoctor() != null ? consultation.getDoctor().getId() : null)
                .nurseId(consultation.getNurse() != null ? consultation.getNurse().getId() : null)
                .scheduleAt(consultation.getScheduledAt())
                .completedAt(consultation.getCompletedAt())
                .status(Status.valueOf(consultation.getStatus().name()))
                .diagnosticsId(diagnosticId)
                .symptomsId(symptomId)
                .build();
    }

}
