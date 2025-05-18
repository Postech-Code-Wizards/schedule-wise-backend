package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class PatientResponse {
    Long id;
    User user;
    EmergencyContact emergencyContact;
    Phone phone;
    LocalDate dateOfBirth;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;

}
