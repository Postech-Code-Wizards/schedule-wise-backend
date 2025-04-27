package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Patient {
    private Long id;
    private User user;
    private EmergencyContact emergencyContact;
    private Phone phone;
    private ZonedDateTime dateOfBirth;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
