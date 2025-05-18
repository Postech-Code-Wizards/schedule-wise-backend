package com.scheduling.wise.converter;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.NurseRequest;
import com.scheduling.wise.domain.dtos.response.NurseResponse;
import com.scheduling.wise.domain.dtos.response.PhoneResponse;
import com.scheduling.wise.gateway.database.entities.NurseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NurseConverter {
    public Nurse toDomain(NurseRequest request) {
        if (request == null) return null;

        List<Phone> phones = null;
        if (request.getPhoneIds() != null) {
            phones = request.getPhoneIds().stream()
                    .map(phoneResponse -> new Phone(phoneResponse.getId())) // assumindo PhoneResponse tem id
                    .collect(Collectors.toList());
        }

        return new Nurse(
                request.getId(),
                new User(request.getUserId()),
                phones,
                request.getAreaOfWork(),
                null,
                null
        );
    }

    public NurseRequest toRequest(Nurse nurse) {
        if (nurse == null) return null;

        List<PhoneResponse> phoneResponses = null;
        if (nurse.getPhones() != null) {
            phoneResponses = nurse.getPhones().stream()
                    .map(phone -> new PhoneResponse(phone.getId()))
                    .collect(Collectors.toList());
        }

        return new NurseRequest(
                nurse.getId(),
                nurse.getUser() != null ? nurse.getUser().getId() : null,
                phoneResponses,
                nurse.getAreaOfWork()
        );
    }

    public NurseResponse toResponse(Nurse nurse) {
        if (nurse == null) return null;
        return new NurseResponse(
                nurse.getId(),
                nurse.getUser(),
                nurse.getPhones(),
                nurse.getAreaOfWork(),
                nurse.getCreatedAt(),
                nurse.getUpdatedAt()
        );
    }

    public Nurse toDomain(NurseEntity entity) {
        if (entity == null) return null;

        // Conversão simplificada das phones (PhoneEntity → Phone)
        List<Phone> phones = null;
        if (entity.getPhones() != null) {
            phones = entity.getPhones().stream()
                    .map(pe -> new Phone(pe.getId()))
                    .collect(Collectors.toList());
        }

        return new Nurse(
                entity.getId(),
                new User(entity.getUser()),
                phones,
                entity.getAreaOfWork(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public NurseEntity toEntity(Nurse nurse) {
        if (nurse == null) return null;
        NurseEntity entity = new NurseEntity();
        entity.setId(nurse.getId());
        entity.setUser(nurse.getUser() != null ? nurse.getUser().getId() : null);
        entity.setAreaOfWork(nurse.getAreaOfWork());
        entity.setCreatedAt(nurse.getCreatedAt());
        entity.setUpdatedAt(nurse.getUpdatedAt());
        // Phones e Consultations devem ser mapeados separadamente
        return entity;
    }

    public List<Nurse> toDomain(List<NurseEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<NurseResponse> toResponse(List<Nurse> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
