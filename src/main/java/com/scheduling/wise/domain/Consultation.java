package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

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
    private ZonedDateTime scheduledAt;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime completedAt;

    public Consultation(Long id){
        this.id = id;
    }
}
