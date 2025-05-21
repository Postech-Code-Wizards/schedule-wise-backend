package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.Symptom;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class DiagnosticsResponse {
    Long id;
    Consultation consultation;
    Patient patient;
    Doctor doctor;
    Symptom symptom;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;

}
