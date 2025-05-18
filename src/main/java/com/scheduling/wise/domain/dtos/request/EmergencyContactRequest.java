package com.scheduling.wise.domain.dtos.request;

import com.scheduling.wise.domain.enums.RelationshipType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmergencyContactRequest {
    Long id;
    Long patientId;
    String contactName;
    Long phoneId;
    RelationshipType relationshipType;
}
