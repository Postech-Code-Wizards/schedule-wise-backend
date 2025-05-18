package com.scheduling.wise.domain.dtos.request;

import com.scheduling.wise.domain.dtos.response.PhoneResponse;
import com.scheduling.wise.domain.enums.AreaOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NurseRequest {
    Long id;
    Long userId;
    List<PhoneResponse> phoneIds;
    AreaOfWork areaOfWork;

}
