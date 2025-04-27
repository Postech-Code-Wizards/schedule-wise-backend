package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Diagnostics {
    private Long id;
    private Consultation consultation;
    private Patient patient;
    private Doctor doctor;
    private Symptoms symptoms;
    private PrescriptionDetails prescriptionDetails;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
