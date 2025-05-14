package com.scheduling.wise.mappers;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.dtos.request.RegisterConsultationRequest;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ConsultationRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    @Mapping(target = "status", expression = "java(com.scheduling.wise.domain.enums.Status.SCHEDULED)")
    @Mapping(target = "patient", source = "patientId", qualifiedByName = "mapPatientId")
    @Mapping(target = "doctor", source = "doctorId", qualifiedByName = "mapDoctorId")
    @Mapping(target = "nurse", source = "nurseId", qualifiedByName = "mapNurseId")
    Consultation toConsultation(RegisterConsultationRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "diagnostics", ignore = true)
    ConsultationEntity toConsultationEntity(Consultation consultation);

    @Named("mapPatientId")
    default Patient mapPatientId(Long id) {
        return new Patient(id);
    }

    @Named("mapDoctorId")
    default Doctor mapDoctorId(Long id) {
        return new Doctor(id);
    }

    @Named("mapNurseId")
    default Nurse mapNurseId(Long id) {
        return new Nurse(id);
    }
}
