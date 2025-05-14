package com.scheduling.wise.dtos.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;

public record RegisterConsultationRequest(
        @Valid @NotNull(message = "Patient data must be complete") Long patientId,
        @Valid @NotNull(message = "A Doctor must be assigned for this consultation") Long doctorId,
        @Valid @NotNull(message = "At least one nurse must be assigned for this consultation") Long nurseId,
        @NotNull(message = "A scheduling should have a date to be set") ZonedDateTime scheduledAt
) {
}
