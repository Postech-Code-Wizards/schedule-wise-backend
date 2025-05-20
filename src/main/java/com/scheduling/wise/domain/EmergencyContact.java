package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.RelationshipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
@AllArgsConstructor
public class EmergencyContact {
    private Long id;
    private Patient patient;
    private String contactName;
    private Phone phone;
    private RelationshipType relationshipType;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
