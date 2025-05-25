package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.enums.RelationshipType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class EmergencyContactResponse {
    Long id;
    String contactName;
    Phone phone;
    RelationshipType relationshipType;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
}
