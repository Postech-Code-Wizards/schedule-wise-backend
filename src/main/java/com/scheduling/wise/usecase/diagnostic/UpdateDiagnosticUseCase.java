package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.DiagnosticGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateDiagnosticUseCase {
    private final DiagnosticGateway diagnosticGateway;

    @Transactional
    public Diagnostic execute(Long id, Diagnostic diagnostic) {
        return diagnosticGateway.update(id, diagnostic);
    }
}
