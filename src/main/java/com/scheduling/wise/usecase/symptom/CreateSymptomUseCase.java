package com.scheduling.wise.usecase.symptom;

import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.SymptomGateway;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSymptomUseCase {
    private final SymptomGateway symptomGateway;

    public SymptomEntity execute(Symptom symptom) {
        return symptomGateway.save(symptom);
    }
}
