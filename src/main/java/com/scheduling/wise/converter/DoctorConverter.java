package com.scheduling.wise.converter;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.DoctorRequest;
import com.scheduling.wise.domain.dtos.response.DoctorResponse;
import com.scheduling.wise.gateway.database.entities.DoctorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DoctorConverter {

    private final UserConverter userConverter;
    private final PhoneConverter phoneConverter;

    public Doctor toDomain(DoctorRequest request) {
        if (request == null) return null;

        User user = request.getUser() != null ? new User(request.getUser()) : null;
        Phone phone = request.getPhone() != null ? new Phone(request.getPhone()) : null;

        return new Doctor(
                request.getId(),
                user,
                phone,
                request.getSpecialty(),
                null,  // consultas ignoradas aqui
                request.getCrm(),
                null,
                null
        );
    }

    public DoctorResponse toResponse(Doctor doctor) {
        if (doctor == null) return null;

        return new DoctorResponse(
                doctor.getId(),
                doctor.getUser(),
                doctor.getPhone(),
                doctor.getSpecialty(),
                doctor.getConsultations(),
                doctor.getCrm(),
                doctor.getCreatedAt(),
                doctor.getUpdatedAt()
        );
    }

    public Doctor toDomain(DoctorEntity entity) {
        if (entity == null) return null;

        User user = userConverter.toDomain(entity.getUser());
        Phone phone = phoneConverter.toDomain(entity.getPhone());

        return new Doctor(
                entity.getId(),
                user,
                phone,
                entity.getSpecialty(),
                null,  // consultas ignoradas
                entity.getCrm(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public DoctorEntity toEntity(Doctor doctor) {
        if (doctor == null) return null;

        DoctorEntity entity = new DoctorEntity();
        entity.setId(doctor.getId());
        entity.setUser(userConverter.toEntity(doctor.getUser()));
        entity.setPhone(phoneConverter.toEntity(doctor.getPhone()));
        entity.setSpecialty(doctor.getSpecialty());
        entity.setCrm(doctor.getCrm());
        entity.setCreatedAt(doctor.getCreatedAt());
        entity.setUpdatedAt(doctor.getUpdatedAt());

        return entity;
    }

    public List<Doctor> toDomain(List<DoctorEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<DoctorResponse> toResponse(List<Doctor> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}