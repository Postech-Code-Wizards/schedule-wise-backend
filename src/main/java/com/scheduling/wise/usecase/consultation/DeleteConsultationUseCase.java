package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.gateway.ConsultationGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    public void execute(Long id) {
        consultationGateway.delete(id);
    }
}
