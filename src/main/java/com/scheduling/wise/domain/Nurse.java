package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.AreaOfWork;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Nurse {
    private Long id;
    private List<Phone> phones;
    private AreaOfWork areaOfWork;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
