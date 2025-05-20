package com.scheduling.wise.converter;

import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.NurseRequest;
import com.scheduling.wise.domain.dtos.response.NurseResponse;
import com.scheduling.wise.gateway.database.entities.NurseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NurseConverter {

    private final UserConverter userConverter;
    private final PhoneConverter phoneConverter;

    public Nurse toDomain(NurseRequest request) {
        if (request == null) return null;

        User user = request.getUser() != null ? new User(request.getUser()) : null;
        Phone phone = request.getPhone() != null ? new Phone(request.getPhone()) : null;

        return new Nurse(
                request.getId(),
                user,
                phone,
                request.getAreaOfWork(),
                null,
                null
        );
    }

    public NurseResponse toResponse(Nurse nurse) {
        if (nurse == null) return null;

        return new NurseResponse(
                nurse.getId(),
                nurse.getUser(),
                nurse.getPhone(),
                nurse.getAreaOfWork(),
                nurse.getCreatedAt(),
                nurse.getUpdatedAt()
        );
    }

    public Nurse toDomain(NurseEntity entity) {
        if (entity == null) return null;

        User user = userConverter.toDomain(entity.getUser());
        Phone phone = phoneConverter.toDomain(entity.getPhone());

        return new Nurse(
                entity.getId(),
                user,
                phone,
                entity.getAreaOfWork(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public NurseEntity toEntity(Nurse nurse) {
        if (nurse == null) return null;

        NurseEntity entity = new NurseEntity();
        entity.setId(nurse.getId());
        entity.setUser(userConverter.toEntity(nurse.getUser()));
        entity.setPhone(phoneConverter.toEntity(nurse.getPhone()));
        entity.setAreaOfWork(nurse.getAreaOfWork());
        entity.setCreatedAt(nurse.getCreatedAt());
        entity.setUpdatedAt(nurse.getUpdatedAt());

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
