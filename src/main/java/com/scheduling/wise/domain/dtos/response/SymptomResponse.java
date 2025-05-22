package com.scheduling.wise.domain.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class SymptomResponse {
    Long id;
    String name;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
}
