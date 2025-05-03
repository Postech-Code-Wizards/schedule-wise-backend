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

@NamedStoredProcedureQuery(
        name = "ConsultationEntity.getFutureConsultations",
        procedureName = "get_future_consultations",
        resultSetMappings = "ConsultationMapping",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patient_id", type = Long.class)
        }
)
@NamedStoredProcedureQuery(
        name = "ConsultationEntity.getPatientConsultationHistory",
        procedureName = "get_patient_consultation_history",
        resultSetMappings = "ConsultationMapping",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_patient_id", type = Long.class)
        }
)
@SqlResultSetMapping(
        name = "ConsultationMapping",
        classes = @ConstructorResult(
                targetClass = com.scheduling.wise.gateway.database.entities.proceduresdtos.ConsultationSummaryDTO.class,
                columns = {
                        @ColumnResult(name = "consultation_id", type = Long.class),
                        @ColumnResult(name = "doctor_id", type = Long.class),
                        @ColumnResult(name = "nurse_id", type = Long.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name = "scheduled_at", type = ZonedDateTime.class),
                        @ColumnResult(name = "completed_at", type = ZonedDateTime.class)
                }
        )
)

@Entity
@Table(
        name = "diagnostic",
        indexes = {
                @Index(name = "idx_consultation_patient_id", columnList = "patient_id"),
                @Index(name = "idx_consultation_scheduled_at", columnList = "scheduled_at"),
                @Index(name = "idx_consultation_status", columnList = "status")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private ConsultationEntity consultation;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "diagnostic", cascade = CascadeType.ALL)
    private List<SymptomEntity> symptoms;

    @OneToMany(mappedBy = "diagnostic", cascade = CascadeType.ALL)
    private List<PrescriptionDetailsEntity> prescriptionDetails;
}