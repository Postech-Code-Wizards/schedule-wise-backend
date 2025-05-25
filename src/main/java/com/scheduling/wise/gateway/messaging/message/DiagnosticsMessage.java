package com.scheduling.wise.gateway.messaging.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DiagnosticsMessage {
    private Long id;
    private Long consultation;
    private Long patient;
    private Long doctor;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
