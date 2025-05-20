package com.scheduling.wise.domain.dtos.request;

import com.scheduling.wise.domain.enums.AreaOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NurseRequest {
    Long id;
    Long user;
    Long phone;
    AreaOfWork areaOfWork;

}
