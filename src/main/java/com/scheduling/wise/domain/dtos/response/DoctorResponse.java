package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class DoctorResponse {
    Long id;
    User user;
    Phone phone;
    String email;
    Specialty specialty;
    List<Consultation> consultations;
    String crm;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;

}
