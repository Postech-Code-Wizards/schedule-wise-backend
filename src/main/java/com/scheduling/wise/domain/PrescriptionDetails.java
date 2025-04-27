package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class PrescriptionDetails {
    private Long id;
    private String medicationName;
    private String dosage;
    private String frequency;
    private String administrationForm;
    private String instructions;
    private ZonedDateTime followUpDate;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
