package com.scheduling.wise.dtos.response;

import com.scheduling.wise.domain.enums.Status;

import java.time.ZonedDateTime;

public record ConsultationResponse(
        Long id,
        PatientResponse patientResponse,
        DoctorResponse doctorResponse,
        NurseResponse nurseResponse,
        Status status,
        ZonedDateTime scheduledAt,
        ZonedDateTime completedAt
) {
}
