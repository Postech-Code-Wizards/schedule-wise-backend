package com.scheduling.wise.dtos.response;

import com.scheduling.wise.domain.enums.Status;

import java.time.ZonedDateTime;

public record ListConsultationResponse(
        Long id,
        PatientResponse patientId,
        DoctorResponse doctorId,
        NurseResponse nurseId,
        Status status,
        ZonedDateTime scheduledAt,
        ZonedDateTime completedAt
) {
}
