package com.scheduling.wise.gateway.database;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.exceptions.ConsultationNotFoundException;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.entities.proceduresdtos.ConsultationSummaryDTO;
import com.scheduling.wise.gateway.database.repositories.ConsultationRepository;
import com.scheduling.wise.mappers.ConsultationRequestMapper;
import com.scheduling.wise.mappers.ConsultationResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultationJpaGateway implements ConsultationGateway {
    private final ConsultationRepository consultationRepository;
    private final ConsultationRequestMapper consultationRequestMapper;
    private final ConsultationResponseMapper consultationResponseMapper;

    @Override
    public void saveConsultation(Consultation consultation) {
        var consultationEntity = consultationRequestMapper.toConsultationEntity(consultation);
        consultationRepository.save(consultationEntity);
    }

    @Override
    public List<Consultation> listUserConsultationsByUserId(Long id) {
        List<ConsultationSummaryDTO> consultationEntity = consultationRepository.getFutureConsultations(id);
        return consultationResponseMapper.toConsultation(consultationEntity);
    }

    @Override
    public Consultation getConsultationById(Long id) {
        ConsultationEntity consultationEntity = consultationRepository.findById(id)
                .orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for id " + id));
        return consultationResponseMapper.toConsultation(consultationEntity);
    }

    @Override
    public void deleteConsultationById(Long id){
        consultationRepository.deleteById(id);
    }
}
