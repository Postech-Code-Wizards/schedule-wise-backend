package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.PrescriptionDetailsConverter;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.PrescriptionDetailsGateway;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import com.scheduling.wise.gateway.database.repositories.PrescriptionDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PrescriptionDetailsJpaRepository implements PrescriptionDetailsGateway {
    private final PrescriptionDetailsRepository repository;
    private final PrescriptionDetailsConverter converter;

    @Override
    public PrescriptionDetailsEntity save(PrescriptionDetails prescriptionDetails) {
        return repository.save(converter.toEntity(prescriptionDetails));
    }

    @Override
    public List<PrescriptionDetails> getAll() {
        return converter.toDomain(repository.findAll());
    }

    @Override
    public PrescriptionDetails getById(Long id) {
        return converter.toDomain(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prescription details not found for id " + id)));
    }

    @Override
    public void update(Long id, PrescriptionDetails prescriptionDetails) {
        PrescriptionDetailsEntity newPrescriptionDetailsEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prescription details not found for id " + id));
        PrescriptionDetailsEntity prescriptionDetailsEntity = converter.toEntity(getById(id));

        prescriptionDetailsEntity.setDosage(newPrescriptionDetailsEntity.getDosage());
        prescriptionDetailsEntity.setFrequency(newPrescriptionDetailsEntity.getFrequency());
        prescriptionDetailsEntity.setInstructions(newPrescriptionDetailsEntity.getInstructions());
        prescriptionDetailsEntity.setFollowUpDate(newPrescriptionDetailsEntity.getFollowUpDate());
        prescriptionDetailsEntity.setMedicationName(newPrescriptionDetailsEntity.getMedicationName());
        prescriptionDetailsEntity.setRouteOfAdministration(newPrescriptionDetailsEntity.getRouteOfAdministration());
        repository.save(prescriptionDetailsEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
