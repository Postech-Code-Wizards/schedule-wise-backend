package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.DiagnosticConverter;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.DiagnosticGateway;
import com.scheduling.wise.gateway.database.entities.DiagnosticEntity;
import com.scheduling.wise.gateway.database.repositories.DiagnosticsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DiagnosticsJpaGateway implements DiagnosticGateway {
    private final DiagnosticsRepository diagnosticsRepository;
    private final DiagnosticConverter converter;

    @Override
    public void save(Diagnostic diagnostic) {
        diagnosticsRepository.save(converter.toEntity(diagnostic));
    }

    @Override
    public List<Diagnostic> getAll() {
        return converter.toDomain(diagnosticsRepository.findAll());
    }

    @Override
    public Diagnostic getById(Long id) {
        return converter.toDomain(diagnosticsRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Diagnostic not found for id " + id)));
    }

    @Override
    public void update(Long id, Diagnostic newDiagnostic) {
        DiagnosticEntity newDiagnosticEntity = diagnosticsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Diagnostic not found for id " + id));
        DiagnosticEntity diagnosticEntity = converter.toEntity(getById(id));

        diagnosticEntity.setDoctor(newDiagnosticEntity.getDoctor());
        diagnosticEntity.setConsultation(newDiagnosticEntity.getConsultation());
        diagnosticEntity.setPatient(newDiagnosticEntity.getPatient());
        diagnosticEntity.setSymptoms(newDiagnosticEntity.getSymptoms());
        diagnosticEntity.setPrescriptionDetails(newDiagnosticEntity.getPrescriptionDetails());
        diagnosticsRepository.save(diagnosticEntity);
    }

    @Override
    public void delete(Long id) {
        diagnosticsRepository.deleteById(id);
    }
}
