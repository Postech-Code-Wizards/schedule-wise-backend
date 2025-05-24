package com.scheduling.wise.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class PrescriptionDetailsResponse {
    Long id;
    String medicationName;
    String dosage;
    String frequency;
    String routeOfAdministration;
    String instructions;
    ZonedDateTime followUpDate;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
}
