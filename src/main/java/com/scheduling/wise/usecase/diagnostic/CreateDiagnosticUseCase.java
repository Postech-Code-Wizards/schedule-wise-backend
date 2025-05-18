package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.DiagnosticGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDiagnosticUseCase {
    private final DiagnosticGateway diagnosticGateway;

    public void execute(Diagnostic diagnostic) {
        diagnosticGateway.save(diagnostic);
    }
}
