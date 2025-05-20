package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.DiagnosticConverter;
import com.scheduling.wise.converter.PrescriptionDetailsConverter;
import com.scheduling.wise.converter.SymptomConverter;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.domain.dtos.request.CreateDiagnosticInput;
import com.scheduling.wise.domain.dtos.request.DiagnosticRequest;
import com.scheduling.wise.domain.dtos.request.PrescriptionDetailsRequest;
import com.scheduling.wise.domain.dtos.request.SymptomRequest;
import com.scheduling.wise.domain.dtos.response.DiagnosticsResponse;
import com.scheduling.wise.usecase.diagnostic.*;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class DiagnosticResolver {
    private final CreateDiagnosticUseCase createDiagnosticUseCase;
    private final GetDiagnosticUseCase getDiagnosticUseCase;
    private final GetAllDiagnosticUseCase getAllDiagnosticUseCase;
    private final UpdateDiagnosticUseCase updateDiagnosticUseCase;
    private final DeleteDiagnosticUseCase deleteDiagnosticUseCase;

    private final DiagnosticConverter diagnosticConverter;
    private final SymptomConverter symptomConverter;
    private final PrescriptionDetailsConverter prescriptionDetailsConverter;

    @MutationMapping
    public void createDiagnostic(@Argument("input") CreateDiagnosticInput createDiagnosticInput) {

        Diagnostic diagnostic = diagnosticConverter.toDomain(createDiagnosticInput.getDiagnostic());
        List<Symptom> symptoms = createDiagnosticInput.getSymptomInput().stream().map(symptomConverter::toDomain).toList();
        List<PrescriptionDetails> prescriptionDetails = createDiagnosticInput.getPrescriptionDetails().stream().map(prescriptionDetailsConverter::toDomain).toList();
        createDiagnosticUseCase.execute(diagnostic, symptoms, prescriptionDetails);
    }

    @QueryMapping
    public DiagnosticsResponse getDiagnosticById(@Argument("id") Long id) {
        var domain = getDiagnosticUseCase.execute(id);
        return diagnosticConverter.toResponse(domain);
    }

    @QueryMapping
    public List<DiagnosticsResponse> getConsultationDiagnostics(@Argument("id") Long consultationId) {
        var domain = getAllDiagnosticUseCase.execute(consultationId);
        return diagnosticConverter.toResponse(domain);
    }

    @MutationMapping
    public void updateDiagnostic(@Argument("id") Long id,
                                 @Argument("input") DiagnosticRequest diagnosticRequest) {
        Diagnostic domain = diagnosticConverter.toDomain(diagnosticRequest);
        updateDiagnosticUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deleteDiagnostic(@Argument("id") Long id) {
        deleteDiagnosticUseCase.execute(id);
    }

}
