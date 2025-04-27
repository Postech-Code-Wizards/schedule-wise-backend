package com.scheduling.wise.gateway.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "diagnostics")
@Getter
@Setter
@AllArgsConstructor
public class DiagnosticsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "consultation_id", nullable = false)
    private ConsultationEntity consultationEntity;

    @Column(name = "patient_id", nullable = false)
    private PatientEntity patientEntity;

    @Column(name = "doctor_id", nullable = false)
    private DoctorEntity doctorEntity;

    @Column(name = "symptoms_id", nullable = false)
    private SymptomsEntity symptomsEntity;

    @Column(name = "prescriptions_details_id", nullable = false)
    private PrescriptionDetailsEntity prescriptionDetailsEntity;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
