package com.scheduling.wise.usecase.symptom;

import com.scheduling.wise.converter.SymptomConverter;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.SymptomGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllSymptomsByDiagnosticIdUseCase {
    private final SymptomGateway symptomGateway;

    private final SymptomConverter converter;

    public List<Symptom> execute(Long diagnosticId) {
        return converter.toDomain(symptomGateway.getAllByDiagnosticId(diagnosticId));
    }
}
