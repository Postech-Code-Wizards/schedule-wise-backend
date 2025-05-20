package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.AreaOfWork;
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
public class Nurse {
    private Long id;
    private User user;
    private Phone phone;
    private AreaOfWork areaOfWork;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Nurse(Long id) {
        this.id = id;
    }
}
