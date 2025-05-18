package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Long id;
    private User user;
    private Phone phone;
    private String email;
    private Specialty specialty;
    private List<Consultation> consultations;
    private String crm;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Doctor(Long id){
        this.id = id;
    }

}
