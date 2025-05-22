package com.scheduling.wise.gateway.messaging.message;

import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviousConsultationsRequest {
    private Long id;
    private Long consultationId;
    private Long patientId;
    private Long doctorId;
    private Long nurseId;
    private ZonedDateTime scheduleAt;
    private ZonedDateTime completedAt;
    private Status status;
    private Long diagnosticsId;
    private Long symptomsId;
    private Long prescriptionsDetailsId;
}