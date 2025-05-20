package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.Diagnostic;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ConsultationResponse {
    Long id;
    Patient patient;
    Doctor doctor;
    Nurse nurse;
    Status status;
    List<Diagnostic> diagnostics;
    ZonedDateTime scheduledAt;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
    ZonedDateTime completedAt;

}
