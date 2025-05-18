package com.scheduling.wise.domain.dtos.request;


import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ConsultationRequest {
    Long id;
    Long patientId;
    Long doctorId;
    Long nurseId;
    Status status;
    ZonedDateTime scheduledAt;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
    ZonedDateTime completedAt;

}
