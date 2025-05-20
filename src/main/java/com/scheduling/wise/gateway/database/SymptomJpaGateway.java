package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.SymptomConverter;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.gateway.SymptomGateway;
import com.scheduling.wise.gateway.database.entities.SymptomEntity;
import com.scheduling.wise.gateway.database.repositories.SymptomsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SymptomJpaGateway implements SymptomGateway {
    private final SymptomsRepository repository;
    private final SymptomConverter converter;

    @Override
    public SymptomEntity save(Symptom symptom) {
        return repository.save(converter.toEntity(symptom));
    }

    @Override
    public List<Symptom> getAll() {
        return converter.toDomain(repository.findAll());
    }

    @Override
    public Symptom getById(Long id) {
        return converter.toDomain(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Symptom not found for id " + id)));
    }

    @Override
    public void update(Long id, Symptom symptom) {
        SymptomEntity newSymptomEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Symptom not found for id " + id));
        SymptomEntity symptomEntity = converter.toEntity(getById(id));

        symptomEntity.setName(newSymptomEntity.getName());
        repository.save(symptomEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
