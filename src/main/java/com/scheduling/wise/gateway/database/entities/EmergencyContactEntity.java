package com.scheduling.wise.gateway.database.entities;

import com.scheduling.wise.domain.enums.RelationshipType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.ZonedDateTime;

@Entity
@Table(name = "emergency_contact")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id", nullable = false)
    private PhoneEntity phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship_type", nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private RelationshipType relationshipType;
}