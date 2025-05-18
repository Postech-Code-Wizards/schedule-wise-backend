package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.DiagnosticGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetDiagnosticUseCase {
    private final DiagnosticGateway diagnosticGateway;

    public Diagnostic execute(Long id) {
        return diagnosticGateway.getById(id);
    }
}
