package com.scheduling.wise.domain.dtos.request;


import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DoctorRequest {
    Long id;
    Long user;
    Long phone;
    Specialty specialty;
    List<Consultation> consultations;
    String crm;
}
