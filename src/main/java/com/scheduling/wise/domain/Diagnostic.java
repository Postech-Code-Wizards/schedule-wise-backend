package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostic {
    private Long id;
    private Consultation consultation;
    private Patient patient;
    private Doctor doctor;
    private List<Symptom> symptoms;
    private PrescriptionDetails prescriptionDetails;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Diagnostic(Long id) {
        this.id = id;
    }
}
