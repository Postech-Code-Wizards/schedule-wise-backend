package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.DiagnosticGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateDiagnosticUseCase {
    private final DiagnosticGateway diagnosticGateway;

    public void execute(Long id, Diagnostic diagnostic) {
        diagnosticGateway.update(id, diagnostic);
    }
}
