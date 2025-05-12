package com.scheduling.wise.controller.input;

import lombok.Builder;

@Builder
public record CreateConsultationInput(
        Long doctorId,
        Long patientId,
        Long nurseId,
        String scheduledAt
) {}
