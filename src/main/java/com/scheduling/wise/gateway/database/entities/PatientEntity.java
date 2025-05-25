package com.scheduling.wise.gateway.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "has_allergies", nullable = false)
    private Boolean hasAllergies = false;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emergency_contact_id", nullable = false)
    private EmergencyContactEntity emergencyContact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_id", nullable = false)
    private PhoneEntity phone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<ConsultationEntity> consultations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<DiagnosticEntity> diagnostics;
}