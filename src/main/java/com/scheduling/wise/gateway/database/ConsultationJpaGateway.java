package com.scheduling.wise.gateway.database;

import com.scheduling.wise.config.graphql.exceptions.CustomExceptionHandler;
import com.scheduling.wise.controller.exceptions.ConsultationNotFoundException;
import com.scheduling.wise.converter.ConsultationConverter;
import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.repositories.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultationJpaGateway implements ConsultationGateway {
    private final ConsultationRepository consultationRepository;
    private final ConsultationConverter converter;

    @Override
    public Consultation save(Consultation consultation) {
        var consultationEntity = converter.toEntity(consultation);
        return converter.toDomain(consultationRepository.save(consultationEntity));
    }

    @Override
    public List<Consultation> getAllFutureConsultationsById(Long patientId) {
        List<ConsultationEntity> patientConsultations = consultationRepository.findConsultationByPatientId(patientId);
        List<ConsultationEntity> futureConsultations = patientConsultations.stream().filter(consultation
                -> consultation.getScheduledAt().isAfter(ZonedDateTime.now())).toList();
        return converter.toDomain(futureConsultations);
    }

    @Override
    public List<Consultation> getAllConsultationsByPatientId(Long patientId) {
        return converter.toDomain(consultationRepository.findConsultationByPatientId(patientId));
    }

    @Override
    public Consultation getById(Long id) {
        ConsultationEntity consultationEntity = consultationRepository.findById(id)
                .orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for id " + id));
        return converter.toDomain(consultationEntity);
    }

    @Override
    public Consultation updateCompletion(Long id, Consultation consultation) {
        ConsultationEntity oldConsultation = consultationRepository.findById(id).orElseThrow(() -> new CustomExceptionHandler("error.not_found", "Consultation not found for id " + id));
        if (oldConsultation.getStatus() == Status.ACCOMPLISHED) {
            throw new CustomExceptionHandler("error.already_exists", id);
        }
        oldConsultation.setCompletedAt(consultation.getCompletedAt());
        oldConsultation.setStatus(consultation.getStatus());
        return converter.toDomain(consultationRepository.save(oldConsultation));
    }

    @Override
    public void updateStatus(Long id, Consultation consultation) {
        ConsultationEntity oldConsultation = consultationRepository.findById(id).orElseThrow(() -> new ConsultationNotFoundException("Consultation not found for id " + id));

        oldConsultation.setStatus(consultation.getStatus());
        consultationRepository.save(oldConsultation);
    }

    @Override
    public void delete(Long id) {
        consultationRepository.deleteById(id);
    }
}
