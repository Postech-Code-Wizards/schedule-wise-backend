package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.DiagnosticGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllDiagnosticUseCase {
    private final DiagnosticGateway diagnosticGateway;

    public List<Diagnostic> execute(Long consultationId) {
        return diagnosticGateway.getAll(consultationId);
    }
}
