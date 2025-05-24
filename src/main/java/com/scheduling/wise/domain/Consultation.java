package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.Status;
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
public class Consultation {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private Nurse nurse;
    private Status status;
    private List<Diagnostic> diagnostics;
    private ZonedDateTime scheduledAt;
    private ZonedDateTime completedAt;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Consultation(Long id) {
        this.id = id;
    }
}
