package com.scheduling.wise.usecase.symptom;

import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.SymptomGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetSymptomsUseCase {
    private final SymptomGateway symptomGateway;

    public List<Symptom> execute() {
        return symptomGateway.getAll();
    }
}
