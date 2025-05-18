package com.scheduling.wise.usecase.symptom;

import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.SymptomGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateSymptomUseCase {
    private final SymptomGateway symptomGateway;

    public void execute(Long id, Symptom symptom) {
        symptomGateway.update(id, symptom);
    }
}
