package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.converter.PrescriptionDetailsConverter;
import com.scheduling.wise.converter.SymptomConverter;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.DiagnosticGateway;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpdateDiagnosticUseCase {
    private final DiagnosticGateway diagnosticGateway;

    private final SymptomConverter symptomConverter;
    private final PrescriptionDetailsConverter prescriptionDetailsConverter;

    @Transactional
    public void execute(Long id, Diagnostic diagnostic, List<Symptom> symptoms, List<PrescriptionDetails> prescriptionDetailsList) {
        List<SymptomEntity> symptomEntityList = symptoms.stream().map(symptomConverter::toEntity).toList();
        List<PrescriptionDetailsEntity> prescriptionDetailsEntityList = prescriptionDetailsList.stream().map(prescriptionDetailsConverter::toEntity).toList();
        diagnosticGateway.update(id, diagnostic, symptomEntityList, prescriptionDetailsEntityList);
    }
}
