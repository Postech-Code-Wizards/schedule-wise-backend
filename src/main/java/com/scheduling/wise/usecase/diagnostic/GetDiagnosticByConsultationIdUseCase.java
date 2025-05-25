package com.scheduling.wise.usecase.diagnostic;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.DiagnosticGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDiagnosticByConsultationIdUseCase {

    private final DiagnosticGateway diagnosticGateway;

    public List<Diagnostic> execute(Long consultationId) {
        return diagnosticGateway.getByConsultationId(consultationId);
    }
}
