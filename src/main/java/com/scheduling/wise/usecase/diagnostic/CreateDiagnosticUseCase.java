package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.converter.PrescriptionDetailsConverter;
import com.scheduling.wise.converter.SymptomConverter;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.DiagnosticGateway;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import com.scheduling.wise.usecase.consultation.GetConsultationUseCase;
import com.scheduling.wise.usecase.doctor.GetDoctorUseCase;
import com.scheduling.wise.usecase.patient.GetPatientUseCase;
import com.scheduling.wise.usecase.prescriptiondetails.CreatePrescriptionDetailsCase;
import com.scheduling.wise.usecase.symptom.CreateSymptomUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateDiagnosticUseCase {
    private final DiagnosticGateway diagnosticGateway;

    private final CreatePrescriptionDetailsCase createPrescriptionDetailsCase;

    private final SymptomConverter symptomConverter;
    private final PrescriptionDetailsConverter prescriptionDetailsConverter;

    @Transactional
    public void execute(Diagnostic diagnostic, List<Symptom> symptoms, List<PrescriptionDetails> prescriptionDetails) {
        List<SymptomEntity> symptomEntityList = symptoms.stream().map(symptomConverter::toEntity).toList();
        List<PrescriptionDetailsEntity> prescriptionDetailsEntityList = prescriptionDetails.stream().map(prescriptionDetailsConverter::toEntity).toList();
        diagnosticGateway.save(diagnostic, symptomEntityList, prescriptionDetailsEntityList);
    }
}
