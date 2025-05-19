package com.scheduling.wise.application.service;

import com.scheduling.wise.controller.input.CreateConsultationInput;
import com.scheduling.wise.controller.input.UpdateConsultationInput;
import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.mappers.ConsultationMapper;
import com.scheduling.wise.gateway.database.repositories.ConsultationRepository;
import com.scheduling.wise.gateway.messaging.message.StreamMessage;
import com.scheduling.wise.gateway.messaging.message.enums.DeliveryMethod;
import com.scheduling.wise.gateway.messaging.publisher.NotificationPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConsultationService {

    private final ConsultationRepository repository;
    private final ConsultationMapper mapper;
    private final NotificationPublisher notificationPublisher;

    public Consultation createFromInput(CreateConsultationInput input) {
        Consultation consultation = Consultation.builder()
                .doctor(Doctor.builder().id(input.doctorId()).build())
                .patient(Patient.builder().id(input.patientId()).build())
                .nurse(input.nurseId() != null ? Nurse.builder().id(input.nurseId()).build() : null)
                .scheduledAt(ZonedDateTime.parse(input.scheduledAt()))
                .status(Status.SCHEDULED)
                .build();

        var entity = mapper.toEntity(consultation);
        ConsultationEntity saved = repository.save(entity);
        Consultation created = mapper.toDomain(saved);

        notificationPublisher.publishConsultationCreated(StreamMessage.builder()
                .deliveryMethod(DeliveryMethod.EMAIL)
                .recipient("paciente@email.com")
                .message("Sua consulta foi agendada para " + created.getScheduledAt())
                .build());

        return created;
    }

    public Consultation updateFromInput(UpdateConsultationInput input) {
        Consultation existing = findById(input.id())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        if (input.scheduledAt() != null) {
            existing.setScheduledAt(ZonedDateTime.parse(input.scheduledAt()));
        }

        if (input.status() != null) {
            existing.setStatus(input.status());
        }

        return update(existing.getId(), existing);
    }

    public Consultation create(Consultation consultation) {
        var entity = mapper.toEntity(consultation);
        var saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    public Optional<Consultation> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    public List<Consultation> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    public Consultation update(Long id, Consultation updated) {
        ConsultationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        entity.setScheduledAt(updated.getScheduledAt());
        entity.setStatus(updated.getStatus());
        entity.setCompletedAt(updated.getCompletedAt());

        return mapper.toDomain(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}