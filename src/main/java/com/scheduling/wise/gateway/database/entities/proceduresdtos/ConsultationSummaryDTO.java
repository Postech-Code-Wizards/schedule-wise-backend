package com.scheduling.wise.gateway.database.entities.proceduresdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ConsultationSummaryDTO {
    private Long consultationId;
    private Long doctorId;
    private Long nurseId;
    private String status;
    private ZonedDateTime scheduledAt;
    private ZonedDateTime completedAt;
}
