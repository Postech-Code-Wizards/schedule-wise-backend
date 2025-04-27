package com.scheduling.wise.gateway.database.entities;

import com.scheduling.wise.domain.enums.PhoneType;
import com.scheduling.wise.domain.enums.RelationshipType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "emergency_contacts")
@Getter
@Setter
@AllArgsConstructor
public class EmergencyContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private PatientEntity patientEntityId;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Column(name = "contact_phone_id", nullable = false)
    private PhoneType contactPhoneId;

    @Column(name = "relationship_type", nullable = false)
    private RelationshipType relationShipType;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
