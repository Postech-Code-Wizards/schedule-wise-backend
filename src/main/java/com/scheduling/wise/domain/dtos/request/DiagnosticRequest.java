package com.scheduling.wise.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DiagnosticRequest {
    Long id;
    Long consultationId;
    Long patientId;
    Long doctorId;
    List<Long> symptoms;
    Long prescriptionDetailsIs;

}
