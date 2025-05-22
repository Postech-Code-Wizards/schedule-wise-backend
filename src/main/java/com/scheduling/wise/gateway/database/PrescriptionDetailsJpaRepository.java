package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.PrescriptionDetailsConverter;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.gateway.PrescriptionDetailsGateway;
import com.scheduling.wise.gateway.database.entities.PrescriptionDetailsEntity;
import com.scheduling.wise.gateway.database.repositories.PrescriptionDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PrescriptionDetailsJpaRepository implements PrescriptionDetailsGateway {
    private final PrescriptionDetailsRepository repository;
    private final PrescriptionDetailsConverter converter;

    @Override
    public void save(PrescriptionDetails prescriptionDetails) {
        repository.save(converter.toEntity(prescriptionDetails));
    }

    @Override
    public List<PrescriptionDetailsEntity> getAllByDiagnosticId(Long id) {
        return repository.findByDiagnosticId(id);
    }

    @Override
    public void deleteAll(List<PrescriptionDetailsEntity> prescriptionDetailsEntityList) {
        repository.deleteAll(prescriptionDetailsEntityList);
    }
}
