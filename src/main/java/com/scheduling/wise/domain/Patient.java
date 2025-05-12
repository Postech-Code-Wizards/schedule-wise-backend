package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Patient {
    private Long id;
    private User user;
    private EmergencyContact emergencyContact;
    private Phone phone;
    private LocalDate dateOfBirth;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
