package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.NurseConverter;
import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.gateway.NurseGateway;
import com.scheduling.wise.gateway.database.entities.NurseEntity;
import com.scheduling.wise.gateway.database.repositories.NurseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NurseJpaGateway implements NurseGateway {
    private final NurseRepository repository;
    private final NurseConverter converter;


    @Override
    public void save(Nurse nurse) {
        repository.save(converter.toEntity(nurse));
    }

    @Override
    public List<Nurse> getAll() {
        return converter.toDomain(repository.findAll());
    }

    @Override
    public Nurse getById(Long id) {
        return converter.toDomain(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nurse not found for id " + id)));
    }

    @Override
    public void update(Long id, Nurse nurse) {
        NurseEntity newNurseEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nurse not found for id " + id));
        NurseEntity nurseEntity = converter.toEntity(getById(id));

        nurseEntity.setAreaOfWork(newNurseEntity.getAreaOfWork());
        nurseEntity.setConsultation(newNurseEntity.getConsultation());
        nurseEntity.setPhones(newNurseEntity.getPhones());
        repository.save(nurseEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
