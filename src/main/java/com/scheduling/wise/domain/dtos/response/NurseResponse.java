package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.enums.AreaOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class NurseResponse {
    Long id;
    User user;
    Phone phone;
    AreaOfWork areaOfWork;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;

}
