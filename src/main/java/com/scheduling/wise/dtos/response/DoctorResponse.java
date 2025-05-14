package com.scheduling.wise.dtos.response;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.enums.Specialty;

public record DoctorResponse(
        UserResponse userResponse,
        Phone phone,
        Specialty specialty
) {
}
