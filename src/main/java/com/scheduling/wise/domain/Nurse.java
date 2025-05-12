package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.AreaOfWork;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Nurse {
    private Long id;
    private User user;
    private Phone phone;
    private AreaOfWork areaOfWork;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
