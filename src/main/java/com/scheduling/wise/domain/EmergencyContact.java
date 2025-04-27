package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.PhoneType;
import com.scheduling.wise.domain.enums.RelationshipType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class EmergencyContact {
    private Long id;
    private Patient patient;
    private String contactName;
    private PhoneType contactPhone;
    private RelationshipType relationshipType;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
