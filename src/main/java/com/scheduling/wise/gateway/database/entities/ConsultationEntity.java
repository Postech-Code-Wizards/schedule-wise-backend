package com.scheduling.wise.gateway.database.entities;

import com.scheduling.wise.domain.enums.Status;
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
@Table(name = "consultation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "nurse_id")
    private NurseEntity nurse;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Status status;

    @Column(nullable = false, name = "scheduled_at")
    private ZonedDateTime scheduledAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "completed_at")
    private ZonedDateTime completedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @OneToOne(mappedBy = "consultation", cascade = CascadeType.ALL)
    private DiagnosticEntity diagnostic;
}