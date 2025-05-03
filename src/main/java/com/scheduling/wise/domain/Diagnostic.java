package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Diagnostic {
    private Long id;
    private Consultation consultation;
    private Patient patient;
    private Doctor doctor;
    private Symptom symptom;
    private PrescriptionDetails prescriptionDetails;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
