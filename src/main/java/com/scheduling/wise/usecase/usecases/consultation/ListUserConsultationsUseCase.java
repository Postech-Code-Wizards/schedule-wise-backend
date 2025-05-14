package com.scheduling.wise.usecase.usecases.consultation;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.dtos.response.ListConsultationResponse;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.mappers.ConsultationResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUserConsultationsUseCase {
    private final ConsultationGateway consultationGateway;
    private final ConsultationResponseMapper consultationResponseMapper;

    public List<ListConsultationResponse> execute(Long id) {
        List<Consultation> consultations = consultationGateway.listUserConsultationsByUserId(id);
        return consultationResponseMapper.toResponseConsultationList(consultations);
    }

}
