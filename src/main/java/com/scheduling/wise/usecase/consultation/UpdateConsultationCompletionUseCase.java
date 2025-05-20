package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.gateway.ConsultationGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateConsultationCompletionUseCase {
    private final ConsultationGateway consultationGateway;

    public void execute(Long id, Consultation consultation) {
        consultationGateway.updateCompletion(id, consultation);
    }
}
