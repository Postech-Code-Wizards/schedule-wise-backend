package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.gateway.ConsultationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    public void execute(Consultation consultation) {
        consultationGateway.save(consultation);
    }
}
