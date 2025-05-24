package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.SymptomConverter;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.SymptomGateway;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import com.scheduling.wise.gateway.database.repositories.SymptomsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SymptomJpaGateway implements SymptomGateway {
    private final SymptomsRepository repository;
    private final SymptomConverter converter;

    @Override
    public void save(SymptomEntity symptomEntity) {
        repository.save(symptomEntity);
    }

    @Override
    public List<SymptomEntity> getAllByDiagnosticId(Long id) {
        return repository.findByDiagnosticId(id);
    }

    @Override
    public void deleteAll(List<SymptomEntity> symptomEntityList) {
        repository.deleteAll(symptomEntityList);
    }
}
