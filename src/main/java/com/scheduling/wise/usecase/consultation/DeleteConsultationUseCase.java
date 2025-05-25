package com.scheduling.wise.usecase.consultation;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.ConsultationGateway;
import com.scheduling.wise.usecase.diagnostic.DeleteDiagnosticUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DeleteConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    private final GetConsultationUseCase getConsultationUseCase;
    private final DeleteDiagnosticUseCase deleteDiagnosticUseCase;

    @Transactional
    public void execute(Long id) {
        var consultation = getConsultationUseCase.execute(id);
        if (consultation.getDiagnostics() != null) {
            List<Diagnostic> diagnostics = consultation.getDiagnostics();
            for (Diagnostic diagnostic : diagnostics) {
                deleteDiagnosticUseCase.execute(diagnostic.getId());
            }
        }
        consultationGateway.delete(id);
    }
}
