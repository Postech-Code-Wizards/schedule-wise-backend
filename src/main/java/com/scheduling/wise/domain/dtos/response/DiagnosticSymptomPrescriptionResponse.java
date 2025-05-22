package com.scheduling.wise.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DiagnosticSymptomPrescriptionResponse {
    DiagnosticsResponse diagnostic;
    List<SymptomResponse> symptom;
    List<PrescriptionDetailsResponse> prescriptionDetails;
}
