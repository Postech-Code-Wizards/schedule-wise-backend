package com.scheduling.wise.converter;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.DoctorRequest;
import com.scheduling.wise.domain.dtos.response.DoctorResponse;
import com.scheduling.wise.gateway.database.entities.DoctorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorConverter {
    public Doctor toDomain(DoctorRequest request) {
        if (request == null) return null;
        return new Doctor(
                request.getId(),
                new User(request.getUserId()),
                new Phone(request.getPhoneId()),
                request.getEmail(),
                request.getSpecialty(),
                request.getConsultations(),
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
                doctor.getEmail(),
                doctor.getSpecialty(),
                doctor.getConsultations(),
                doctor.getCrm(),
                doctor.getCreatedAt(),
                doctor.getUpdatedAt()
        );
    }

    public Doctor toDomain(DoctorEntity entity) {
        if (entity == null) return null;
        return new Doctor(
                entity.getId(),
                entity.getUser() != null ? new User(entity.getUser()) : null,
                null, // Phones tratados separadamente, assumindo OneToMany
                null, // Email não está presente diretamente na entidade
                entity.getSpecialty(),
                null, // Consultations idem
                entity.getCrm(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public DoctorEntity toEntity(Doctor doctor) {
        if (doctor == null) return null;
        DoctorEntity entity = new DoctorEntity();
        entity.setId(doctor.getId());
        entity.setUser(doctor.getUser() != null ? doctor.getUser().getId() : null);
        entity.setSpecialty(doctor.getSpecialty());
        entity.setCrm(doctor.getCrm());
        entity.setCreatedAt(doctor.getCreatedAt());
        entity.setUpdatedAt(doctor.getUpdatedAt());
        // Phones, Consultations e Diagnostics devem ser mapeados separadamente
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
