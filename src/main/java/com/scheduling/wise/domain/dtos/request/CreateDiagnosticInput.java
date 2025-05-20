package com.scheduling.wise.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateDiagnosticInput {
    private DiagnosticRequest diagnostic;
    private List<SymptomRequest> symptomInput;
    private List<PrescriptionDetailsRequest> prescriptionDetails;

}
