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
@Table(name = "diagnostic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private ConsultationEntity consultation;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "symptom_id")
    private SymptomEntity symptom;

    @OneToMany(mappedBy = "diagnostic", cascade = CascadeType.ALL)
    private List<PrescriptionDetailsEntity> prescriptionDetails;
}