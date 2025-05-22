package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.*;
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
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;

}
