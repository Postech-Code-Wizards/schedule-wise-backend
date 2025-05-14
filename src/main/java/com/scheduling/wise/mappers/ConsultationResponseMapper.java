package com.scheduling.wise.mappers;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.enums.Status;
import com.scheduling.wise.dtos.response.ListConsultationResponse;
import com.scheduling.wise.dtos.response.ConsultationResponse;
import com.scheduling.wise.gateway.database.entities.ConsultationEntity;
import com.scheduling.wise.gateway.database.entities.proceduresdtos.ConsultationSummaryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultationResponseMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(com.scheduling.wise.domain.enums.Status.SCHEDULED)")
    @Mapping(target = "patient", source = "patient", qualifiedByName = "mapPatientId")
    @Mapping(target = "doctor", source = "doctor", qualifiedByName = "mapDoctorId")
    @Mapping(target = "nurse", source = "nurse", qualifiedByName = "mapNurseId")
    @Mapping(target = "scheduledAt")
    @Mapping(target = "completedAt")
    Consultation toConsultation(ConsultationEntity consultationEntity);

    List<Consultation> toConsultation(List<ConsultationSummaryDTO> summaryDtos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(com.scheduling.wise.domain.enums.Status.SCHEDULED)")
    @Mapping(target = "patientResponse", source = "patient")
    @Mapping(target = "doctorResponse", source = "doctor")
    @Mapping(target = "nurseResponse", source = "nurse")
    @Mapping(target = "scheduledAt")
    @Mapping(target = "completedAt")
    ConsultationResponse toResponseConsultation(Consultation consultation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(com.scheduling.wise.domain.enums.Status.SCHEDULED)")
    @Mapping(target = "patientId", source = "patient", qualifiedByName = "mapPatientId")
    @Mapping(target = "doctorId", source = "doctor", qualifiedByName = "mapDoctorId")
    @Mapping(target = "nurseId", source = "nurse", qualifiedByName = "mapNurseId")
    @Mapping(target = "scheduledAt")
    @Mapping(target = "completedAt")
    List<ListConsultationResponse> toResponseConsultationList(List<Consultation> consultation);


    default Consultation toConsultation(ConsultationSummaryDTO consultationSummaryDTO) {
        return new Consultation(
                consultationSummaryDTO.getConsultationId(),
                new Patient(consultationSummaryDTO.getPatientId()),
                new Doctor(consultationSummaryDTO.getDoctorId()),
                new Nurse(consultationSummaryDTO.getNurseId()),
                Status.valueOf(consultationSummaryDTO.getStatus()),
                consultationSummaryDTO.getScheduledAt(),
                consultationSummaryDTO.getCreatedAt(),
                consultationSummaryDTO.getUpdatedAt(),
                consultationSummaryDTO.getCompletedAt()
        );
    }

    @Named("mapPatientId")
    default Patient mapPatientId(com.scheduling.wise.gateway.database.entities.PatientEntity entity) {
        return new Patient(entity.getId());
    }

    @Named("mapDoctorId")
    default Doctor mapDoctorId(com.scheduling.wise.gateway.database.entities.DoctorEntity entity) {
        return new Doctor(entity.getId());
    }

    @Named("mapNurseId")
    default Nurse mapNurseId(com.scheduling.wise.gateway.database.entities.NurseEntity entity) {
        return new Nurse(entity.getId());
    }

}
