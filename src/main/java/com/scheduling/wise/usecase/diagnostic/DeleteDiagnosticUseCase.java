package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.gateway.DiagnosticGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteDiagnosticUseCase {
    private final DiagnosticGateway diagnosticGateway;

    public void execute(Long id) {
        diagnosticGateway.delete(id);
    }
}
