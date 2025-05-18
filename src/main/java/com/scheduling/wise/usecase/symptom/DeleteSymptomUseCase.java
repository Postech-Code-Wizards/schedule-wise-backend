package com.scheduling.wise.usecase.symptom;

import com.scheduling.wise.gateway.SymptomGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteSymptomUseCase {
    private final SymptomGateway symptomGateway;

    public void execute(Long id) {
        symptomGateway.delete(id);
    }
}
