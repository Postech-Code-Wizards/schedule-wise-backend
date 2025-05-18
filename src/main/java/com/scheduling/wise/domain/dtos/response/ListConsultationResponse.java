package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ListConsultationResponse {
    Long id;
    PatientResponse patientId;
    DoctorResponse doctorId;
    NurseResponse nurseId;
    Status status;
    ZonedDateTime scheduledAt;
    ZonedDateTime completedAt;

}
