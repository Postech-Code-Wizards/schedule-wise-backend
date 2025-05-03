package com.scheduling.wise.gateway.database.entities;

import com.scheduling.wise.domain.enums.AreaOfWork;
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
@Table(name = "nurse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NurseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "area_of_work", nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private AreaOfWork areaOfWork;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

    @OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL)
    private List<ConsultationEntity> consultation;
}
