package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.DoctorConverter;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.gateway.DoctorGateway;
import com.scheduling.wise.gateway.database.repositories.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorJpaGateway implements DoctorGateway {
    private final DoctorConverter converter;
    private final DoctorRepository doctorRepository;

    @Override
    public void save(Doctor doctor) {
        doctorRepository.save(converter.toEntity(doctor));
    }

    @Override
    public List<Doctor> getAll() {
        return converter.toDomain(doctorRepository.findAll());
    }

    @Override
    public Doctor getById(Long id) {
        return converter.toDomain(doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found for id " + id)));
    }

    @Override
    public void update(Long id, Doctor newDoctor) {
        var doctor = doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctonr not found for id " + id));
        var newDoctorEntity = converter.toEntity(newDoctor);
        doctor.setConsultation(newDoctorEntity.getConsultation());
        doctor.setCrm(newDoctorEntity.getCrm());
        doctor.setPhones(newDoctorEntity.getPhones());
        doctor.setDiagnostics(newDoctorEntity.getDiagnostics());
        doctor.setSpecialty(newDoctorEntity.getSpecialty());
        doctorRepository.save(doctor);
    }

    @Override
    public void delete(Long id) {
        doctorRepository.delete(
                converter.toEntity(getById(id)));
    }
}
