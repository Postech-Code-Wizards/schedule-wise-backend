package com.scheduling.wise.usecase.symptom;

import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.SymptomGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSymptomUseCase {
    private final SymptomGateway symptomGateway;

    public void execute(Symptom symptom) {
        symptomGateway.save(symptom);
    }
}
