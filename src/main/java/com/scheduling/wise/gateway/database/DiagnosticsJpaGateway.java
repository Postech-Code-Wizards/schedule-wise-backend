package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.DiagnosticConverter;
import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.gateway.DiagnosticGateway;
import com.scheduling.wise.gateway.SymptomGateway;
import com.scheduling.wise.gateway.database.entities.DiagnosticEntity;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import com.scheduling.wise.gateway.database.repositories.DiagnosticsRepository;
import com.scheduling.wise.gateway.database.repositories.PrescriptionDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DiagnosticsJpaGateway implements DiagnosticGateway {
    private final DiagnosticsRepository diagnosticsRepository;
    private final DiagnosticConverter converter;

    private final SymptomGateway symptomGateway;
    private final PrescriptionDetailsRepository prescriptionDetailsRepository;

    @Override
    public void save(Diagnostic diagnostic, List<SymptomEntity> symptomEntityList, List<PrescriptionDetailsEntity> prescriptionDetailsEntityList) {
        DiagnosticEntity diagnosticEntity = converter.toEntity(diagnostic);
        converter.toDomain(diagnosticsRepository.save(diagnosticEntity));

        symptomEntityList.forEach(symptomEntity -> symptomEntity.setDiagnostic(diagnosticEntity));
        symptomEntityList.forEach(symptomGateway::save);

        prescriptionDetailsEntityList.forEach(prescriptionDetailsEntity -> prescriptionDetailsEntity.setDiagnostic(diagnosticEntity));
        prescriptionDetailsEntityList.forEach(prescriptionDetailsRepository::save);
    }

    @Override
    public List<Diagnostic> getAll(Long consultationId) {
        List<DiagnosticEntity> consultationDiagnostics = new ArrayList<>();
        List<DiagnosticEntity> diagnostics = diagnosticsRepository.findAll();
        for (DiagnosticEntity diagnostic : diagnostics) {
            if (diagnostic.getConsultation().getId().equals(consultationId)) {
                consultationDiagnostics.add(diagnostic);
            }
        }
        return converter.toDomain(consultationDiagnostics);
    }

    @Override
    public Diagnostic getById(Long id) {
        return converter.toDomain(diagnosticsRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Diagnostic not found for id " + id)));
    }

    @Override
    public void update(Long id, Diagnostic newDiagnostic, List<SymptomEntity> symptomEntityList, List<PrescriptionDetailsEntity> prescriptionDetailsEntityList) {
        DiagnosticEntity diagnostic = diagnosticsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Diagnostic not found for id " + id));
        DiagnosticEntity newDiagnosticEntity = converter.toEntity(newDiagnostic);

        diagnostic.setDoctor(newDiagnosticEntity.getDoctor());
        diagnostic.setConsultation(newDiagnosticEntity.getConsultation());
        diagnostic.setPatient(newDiagnosticEntity.getPatient());
        converter.toDomain(diagnosticsRepository.save(diagnostic));

        List<SymptomEntity> oldSymptoms = symptomGateway.getAllByDiagnosticId(id);
        symptomGateway.deleteAll(oldSymptoms);
        symptomEntityList.forEach(symptomEntity -> symptomEntity.setDiagnostic(diagnostic));
        symptomEntityList.forEach(symptomGateway::save);

        List<PrescriptionDetailsEntity> oldPrescriptions = prescriptionDetailsRepository.findByDiagnosticId(id);
        prescriptionDetailsRepository.deleteAll(oldPrescriptions);
        prescriptionDetailsEntityList.forEach(prescriptionDetailsEntity -> prescriptionDetailsEntity.setDiagnostic(diagnostic));
        prescriptionDetailsEntityList.forEach(prescriptionDetailsRepository::save);
    }

    @Override
    public void delete(Long id) {
        List<SymptomEntity> oldSymptoms = symptomGateway.getAllByDiagnosticId(id);
        symptomGateway.deleteAll(oldSymptoms);

        List<PrescriptionDetailsEntity> oldPrescriptions = prescriptionDetailsRepository.findByDiagnosticId(id);
        prescriptionDetailsRepository.deleteAll(oldPrescriptions);

        diagnosticsRepository.deleteById(id);
    }

    @Override
    public List<Diagnostic> getByConsultationId(Long consultationId) {
        return converter.toDomain(diagnosticsRepository.findAllByConsultationId(consultationId));
    }
}
