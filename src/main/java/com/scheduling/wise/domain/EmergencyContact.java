package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.RelationshipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContact {
    private Long id;
    private Patient patient;
    private String contactName;
    private Phone phone;
    private RelationshipType relationshipType;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public EmergencyContact(Long id) {
        this.id = id;
    }
}
