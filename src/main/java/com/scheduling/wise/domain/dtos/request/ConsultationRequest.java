package com.scheduling.wise.domain.dtos.request;


import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

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
    String scheduledAt;
    String completedAt;
}
