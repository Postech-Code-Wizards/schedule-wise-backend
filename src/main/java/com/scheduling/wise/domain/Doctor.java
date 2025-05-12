package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Doctor {
    private Long id;
    private User user;
    private Phone phone;
    private String email;
    private Specialty specialty;
    private String crm;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
