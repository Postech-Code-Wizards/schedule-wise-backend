package com.scheduling.wise.gateway.database;

import com.scheduling.wise.controller.exceptions.ConsultationNotFoundException;
import com.scheduling.wise.converter.ConsultationConverter;
import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.entities.proceduresdtos.ConsultationSummaryDTO;
import com.scheduling.wise.gateway.database.repositories.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultationJpaGateway implements ConsultationGateway {
    private final ConsultationRepository consultationRepository;
    private final ConsultationConverter converter;

    @Override
    public void save(Consultation consultation) {
        var consultationEntity = converter.toEntity(consultation);
        consultationRepository.save(consultationEntity);
    }

    @Override
    public List<Consultation> getAllById(Long id) {
        List<ConsultationSummaryDTO> consultationEntities = consultationRepository.getFutureConsultations(id);
        return converter.toDomain(consultationEntities);
    }

    @Override
    public Consultation getById(Long id) {
        ConsultationEntity consultationEntity = consultationRepository.findById(id)
                .orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for id " + id));
        return converter.toDomain(consultationEntity);
    }

    @Override
    public void updateCompletion(Long id, Consultation consultation) {
        ConsultationEntity newConsultation = consultationRepository.findById(id).orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for id " + id));
        ConsultationEntity consultationEntity = converter.toEntity(consultation);

        consultationEntity.setCompletedAt(newConsultation.getCompletedAt());
        consultationRepository.save(consultationEntity);
    }

    @Override
    public void updateStatus(Long id, Consultation consultation) {
        ConsultationEntity consultationEntity = converter.toEntity(consultation);

        consultationEntity.setStatus(consultationEntity.getStatus());
        consultationRepository.save(consultationEntity);
    }

    @Override
    public void updateDiagnostics(Long id, Consultation consultation, Diagnostic diagnostic) {
        ConsultationEntity newConsultation = consultationRepository.findById(id).orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for id " + id));
        ConsultationEntity consultationEntity = converter.toEntity(consultation);

        consultationEntity.setDiagnostics(newConsultation.getDiagnostics());
        consultationRepository.save(consultationEntity);
    }

    @Override
    public void delete(Long id) {
        consultationRepository.deleteById(id);
    }
}
