package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Consultation {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private Nurse nurse;
    private Status status;
    private ZonedDateTime scheduledAt;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime completedAt;
}
