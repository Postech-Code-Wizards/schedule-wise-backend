package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Diagnostic;

import java.util.List;

public interface ConsultationGateway {
    void save(Consultation consultation);

    List<Consultation> getAllById(Long id);

    Consultation getById(Long id);

    void updateCompletion(Long id, Consultation consultation);

    void updateDiagnostics(Long id, Consultation consultation, Diagnostic diagnostic);

    void updateStatus(Long id, Consultation consultation);

    void delete(Long id);
}
