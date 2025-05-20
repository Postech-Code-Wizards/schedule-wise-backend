package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.PhoneConverter;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.gateway.PhoneGateway;
import com.scheduling.wise.gateway.database.entities.PhoneEntity;
import com.scheduling.wise.gateway.database.repositories.PhoneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PhoneJpaGateway implements PhoneGateway {
    private final PhoneRepository repository;
    private final PhoneConverter converter;

    @Override
    public PhoneEntity save(Phone phone) {
        return repository.save(converter.toEntity(phone));
    }

    @Override
    public List<Phone> getAll() {
        return converter.toDomain(repository.findAll());
    }

    @Override
    public Phone getById(Long id) {
        return converter.toDomain(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found for id " + id)));
    }

    @Override
    public void update(Long id, Phone phone) {
        PhoneEntity newPhoneEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found for id " + id));
        PhoneEntity phoneEntity = converter.toEntity(getById(id));

        phoneEntity.setPhoneNumber(newPhoneEntity.getPhoneNumber());
        phoneEntity.setPhoneType(newPhoneEntity.getPhoneType());
        phoneEntity.setAreaCode(newPhoneEntity.getAreaCode());
        phoneEntity.setOperator(newPhoneEntity.getOperator());
        repository.save(phoneEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
