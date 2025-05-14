package com.scheduling.wise.usecase.usecases.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.gateway.ConsultationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateConsultationUseCase {
    private ConsultationGateway consultationGateway;

    public void execute(Consultation consultation) {
        consultationGateway.saveConsultation(consultation);
    }
}
