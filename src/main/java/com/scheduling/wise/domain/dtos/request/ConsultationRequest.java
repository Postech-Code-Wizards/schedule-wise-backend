package com.scheduling.wise.domain.dtos.request;


import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ConsultationRequest {
    Long id;
    Long patientId;
    Long doctorId;
    Long nurseId;
    Status status;
    List<Long> diagnostics;
    ZonedDateTime scheduledAt;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
    ZonedDateTime completedAt;

}
