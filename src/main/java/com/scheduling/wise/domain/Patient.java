package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Patient {
    private Long id;
    private User user;
    private EmergencyContact emergencyContact;
    private Phone phone;
    private LocalDate dateOfBirth;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Patient(Long id){
        this.id = id;
    }
}
