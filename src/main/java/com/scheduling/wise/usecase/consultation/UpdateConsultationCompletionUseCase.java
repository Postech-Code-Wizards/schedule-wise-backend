package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.gateway.messaging.message.DiagnosticsMessage;
import com.scheduling.wise.gateway.messaging.message.PreviousConsultationsRequest;
import com.scheduling.wise.gateway.messaging.publisher.HistoryProducerService;
import com.scheduling.wise.usecase.diagnostic.GetDiagnosticByConsultationIdUseCase;
import com.scheduling.wise.usecase.diagnostic.GetDiagnosticUseCase;
import com.scheduling.wise.usecase.prescriptiondetails.GetAllPrescriptionDetailsUseCase;
import com.scheduling.wise.usecase.symptom.GetAllSymptomsByDiagnosticIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpdateConsultationCompletionUseCase {
    private final ConsultationGateway consultationGateway;
    private final HistoryProducerService historyProducerService;
    private final GetDiagnosticByConsultationIdUseCase getDiagnosticByConsultationIdUseCase;
    private final GetAllSymptomsByDiagnosticIdUseCase getAllSymptomsByDiagnosticIdUseCase;
    private final GetAllPrescriptionDetailsUseCase getAllPrescriptionDetailsUseCase;

    public void execute(Long id, Consultation consultation) {
        Consultation consultationSaved = consultationGateway.updateCompletion(id, consultation);
        List<Diagnostic> diagnostic = getDiagnosticByConsultationIdUseCase.execute(id);
        List<Symptom> symptomList = getAllSymptomsByDiagnosticIdUseCase.execute(diagnostic.get(0).getId());
        List<PrescriptionDetails> prescriptionDetails = getAllPrescriptionDetailsUseCase.execute(diagnostic.get(0).getId());
        historyProducerService.sendUpdatedHistory(mapToHistoryMessage(consultationSaved, diagnostic, symptomList, prescriptionDetails));
    }

    private PreviousConsultationsRequest mapToHistoryMessage(Consultation consultation,  List<Diagnostic> diagnostic,
                                                             List<Symptom> symptomList, List<PrescriptionDetails> prescriptionDetails) {
        Long firstSymptom = null;
        Long firstPrescription = null;
        Long firstDiagnostic = null;

        if (diagnostic != null && !diagnostic.isEmpty()) {
            firstDiagnostic = diagnostic.getFirst().getId();
        }


        if (symptomList != null && !symptomList.isEmpty()) {
            firstSymptom = symptomList.getFirst().getId();
        }

        if (prescriptionDetails != null && !prescriptionDetails.isEmpty()) {
            firstPrescription = prescriptionDetails.getFirst().getId();
        }

        return PreviousConsultationsRequest.builder()
                .consultationId(consultation.getId())
                .patientId(consultation.getPatient() != null ? consultation.getPatient().getId() : null)
                .doctorId(consultation.getDoctor() != null ? consultation.getDoctor().getId() : null)
                .nurseId(consultation.getNurse() != null ? consultation.getNurse().getId() : null)
                .scheduleAt(consultation.getScheduledAt())
                .completedAt(consultation.getCompletedAt())
                .status(Status.valueOf(consultation.getStatus().name()))
                .diagnostics(firstDiagnostic)
                .symptoms(firstSymptom)
                .prescriptionsDetails(firstPrescription)
                .build();
    }
}
