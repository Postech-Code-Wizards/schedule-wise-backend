package com.scheduling.wise.gateway.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "date_of_birth", nullable = false)
    private ZonedDateTime dateOfBirth;

    @Column(name = "has_allergies")
    private Boolean hasAllergies = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    private PhoneEntity phone;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private EmergencyContactEntity emergencyContact;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<ConsultationEntity> consultations;
}

