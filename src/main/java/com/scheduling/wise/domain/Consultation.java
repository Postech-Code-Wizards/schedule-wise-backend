package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Consultation {
    private Long id;
    private Patient patient;  // Referência para o paciente (não a entidade)
    private Doctor doctor;    // Referência para o médico (não a entidade)
    private Nurse nurse;      // Referência para o enfermeiro (não a entidade)
    private Status status;
    private ZonedDateTime scheduledAt;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime completedAt;
}
