package com.scheduling.wise.usecase.symptom;

import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.SymptomGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetSymptomUseCase {
    private final SymptomGateway symptomGateway;

    public Symptom execute(Long id) {
        return symptomGateway.getById(id);
    }
}
