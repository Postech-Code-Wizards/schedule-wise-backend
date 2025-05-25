package com.scheduling.wise.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PatientRequest {
    Long id;
    Long userId;
    Long emergencyContactId;
    Long phoneId;
    LocalDate dateOfBirth;

}
