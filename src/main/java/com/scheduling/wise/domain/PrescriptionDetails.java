package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDetails {
    private Long id;
    private String medicationName;
    private String dosage;
    private String frequency;
    private String routeOfAdministration;
    private String instructions;
    private ZonedDateTime followUpDate;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public PrescriptionDetails(Long id){
        this.id = id;
    }
}
