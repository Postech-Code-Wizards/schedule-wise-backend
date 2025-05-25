package com.scheduling.wise.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class PrescriptionDetailsRequest {
    Long id;
    String medicationName;
    String dosage;
    String frequency;
    String routeOfAdministration;
    String instructions;
    ZonedDateTime followUpDate;

}
