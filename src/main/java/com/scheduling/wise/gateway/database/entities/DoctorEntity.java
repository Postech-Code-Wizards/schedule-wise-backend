package com.scheduling.wise.gateway.database.entities;

import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.enums.Specialty;
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
import java.util.List;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty", nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Specialty specialty;

    @Column(nullable = false)
    private String crm;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    public Doctor toDomain() {
        return Doctor.builder()
                .id(this.id)
                .phones(this.phones != null
                        ? this.phones.stream().map(PhoneEntity::toDomain).toList()
                        : List.of())
                .specialty(this.specialty)
                .crm(this.crm)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }


}
