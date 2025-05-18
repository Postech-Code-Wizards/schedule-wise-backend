package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.gateway.ConsultationGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    public Consultation execute(Long id) {
        return consultationGateway.getById(id);
    }
}
