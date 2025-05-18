package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.DiagnosticConverter;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.domain.dtos.request.DiagnosticRequest;
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

    private final DiagnosticConverter converter;

    @MutationMapping
    public void createDiagnostic(@Argument("input") DiagnosticRequest diagnosticRequest) {
        createDiagnosticUseCase.execute(converter.toDomain(diagnosticRequest));
    }

    @QueryMapping
    public DiagnosticsResponse getDiagnosticById(@Argument("id") Long id) {
        var domain = getDiagnosticUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<DiagnosticsResponse> getDiagnostics() {
        var domain = getAllDiagnosticUseCase.execute();
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updateDiagnostic(@Argument("id") Long id,
                                 @Argument("input") DiagnosticRequest diagnosticRequest) {
        Diagnostic domain = converter.toDomain(diagnosticRequest);
        updateDiagnosticUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deleteDiagnostic(@Argument("id") Long id) {
        deleteDiagnosticUseCase.execute(id);
    }

}
