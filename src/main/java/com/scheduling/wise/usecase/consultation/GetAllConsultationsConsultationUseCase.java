package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.gateway.ConsultationGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllConsultationsConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    public List<Consultation> execute(Long id) {
        return consultationGateway.getAllConsultationsByPatientId(id);
    }
}
