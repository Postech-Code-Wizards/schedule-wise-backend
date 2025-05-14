package com.scheduling.wise.dtos.response;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.enums.AreaOfWork;

public record NurseResponse(
        UserResponse userResponse,
        Phone phone,
        AreaOfWork areaOfWork
) {
}
