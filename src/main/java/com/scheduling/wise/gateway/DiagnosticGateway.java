package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Diagnostic;

import java.util.List;

public interface DiagnosticGateway {
    void save(Diagnostic diagnostic);

    List<Diagnostic> getAll();

    Diagnostic getById(Long id);

    void update(Long id, Diagnostic newDiagnostic);

    void delete(Long id);
}
