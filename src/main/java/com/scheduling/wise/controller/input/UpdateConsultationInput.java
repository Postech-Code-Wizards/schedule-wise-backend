package com.scheduling.wise.controller.input;

import com.scheduling.wise.domain.enums.Status;
import lombok.Builder;

@Builder
public record UpdateConsultationInput(
        Long id,
        String scheduledAt,
        Status status
) {}
