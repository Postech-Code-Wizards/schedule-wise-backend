package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.EmergencyContactConverter;
import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.gateway.database.entities.EmergencyContactEntity;
import com.scheduling.wise.gateway.database.repositories.EmergencyContactRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmergencyContactJpaGateway implements com.scheduling.wise.gateway.EmergencyContactGateway {
    private final EmergencyContactRepository repository;
    private final EmergencyContactConverter converter;

    @Override
    public void save(EmergencyContact emergencyContact) {
        repository.save(converter.toEntity(emergencyContact));
    }

    @Override
    public List<EmergencyContact> getAll() {
        return converter.toDomain(repository.findAll());
    }

    @Override
    public EmergencyContact getById(Long id) {
        return converter.toDomain(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Emergency Contact not found for id " + id)));
    }

    @Override
    public void update(Long id, EmergencyContact newEmergencyContact) {
        EmergencyContactEntity newEmergencyContactEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Emergency contact not found for id " + id));
        EmergencyContactEntity emergencyContact = converter.toEntity(getById(id));

        emergencyContact.setContactName(newEmergencyContactEntity.getContactName());
        emergencyContact.setPatient(newEmergencyContactEntity.getPatient());
        emergencyContact.setPhone(newEmergencyContactEntity.getPhone());
        emergencyContact.setRelationshipType(newEmergencyContactEntity.getRelationshipType());
        repository.save(emergencyContact);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
