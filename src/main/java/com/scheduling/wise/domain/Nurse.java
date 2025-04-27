package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.AreaOfWork;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Nurse {
    private Long id;
    private User user;
    private Phone phone;
    private AreaOfWork areaOfWork;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
