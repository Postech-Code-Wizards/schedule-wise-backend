package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.DiagnosticConverter;
import com.scheduling.wise.converter.PrescriptionDetailsConverter;
import com.scheduling.wise.converter.SymptomConverter;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.domain.dtos.request.*;
import com.scheduling.wise.domain.dtos.response.DiagnosticSymptomPrescriptionResponse;
import com.scheduling.wise.domain.dtos.response.DiagnosticsResponse;
import com.scheduling.wise.domain.dtos.response.PrescriptionDetailsResponse;
import com.scheduling.wise.domain.dtos.response.SymptomResponse;
import com.scheduling.wise.usecase.diagnostic.*;
import com.scheduling.wise.usecase.prescriptiondetails.GetAllPrescriptionDetailsUseCase;
import com.scheduling.wise.usecase.symptom.GetAllSymptomsByDiagnosticIdUseCase;
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
    private final GetAllSymptomsByDiagnosticIdUseCase getAllSymptomsByDiagnosticIdUseCase;
    private final GetAllPrescriptionDetailsUseCase getAllPrescriptionDetailsUseCase;

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
    public DiagnosticSymptomPrescriptionResponse getDiagnosticById(@Argument("id") Long id) {
        var domain = getDiagnosticUseCase.execute(id);
        var symptom = getAllSymptomsByDiagnosticIdUseCase.execute(id);
        var prescriptionDetails = getAllPrescriptionDetailsUseCase.execute(id);

        DiagnosticsResponse diagnosticsResponse = diagnosticConverter.toResponse(domain);
        List<SymptomResponse> symptomResponseList = symptomConverter.toResponse(symptom);
        List<PrescriptionDetailsResponse> prescriptionDetailsResponseList = prescriptionDetailsConverter.toResponse(prescriptionDetails);

        return new DiagnosticSymptomPrescriptionResponse(diagnosticsResponse, symptomResponseList, prescriptionDetailsResponseList);
    }

    @QueryMapping
    public List<DiagnosticsResponse> getDiagnosticsByConsultationId(@Argument("id") Long consultationId) {
        var domain = getAllDiagnosticUseCase.execute(consultationId);
        return diagnosticConverter.toResponse(domain);
    }

    @MutationMapping
    public void updateDiagnostic(@Argument("id") Long id,
                                 @Argument("input") UpdateDiagnosticInput updateDiagnosticInput) {

        List<Symptom> symptoms = updateDiagnosticInput.getSymptomInput().stream().map(symptomConverter::toDomain).toList();
        List<PrescriptionDetails> prescriptionDetails = updateDiagnosticInput.getPrescriptionDetails().stream().map(prescriptionDetailsConverter::toDomain).toList();
        Diagnostic domain = diagnosticConverter.toDomain(updateDiagnosticInput.getDiagnostic());
        updateDiagnosticUseCase.execute(id, domain, symptoms, prescriptionDetails);
    }

    @MutationMapping
    public void deleteDiagnostic(@Argument("id") Long id) {
        deleteDiagnosticUseCase.execute(id);
    }

}
