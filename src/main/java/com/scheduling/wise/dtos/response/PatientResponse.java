package com.scheduling.wise.dtos.response;

import com.scheduling.wise.domain.Phone;

public record PatientResponse(
        UserResponse userResponse,
        Phone phone
) {
}
