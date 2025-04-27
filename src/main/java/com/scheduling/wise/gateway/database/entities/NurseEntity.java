package com.scheduling.wise.gateway.database.entities;

import com.scheduling.wise.domain.enums.AreaOfWork;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "nurses")
@Getter
@Setter
@AllArgsConstructor
public class NurseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id")
    private UserEntity userEntityId;

    @Column(name = "phone_id")
    private PhoneEntity phoneId;

    @Column(name = "area_of_work")
    private AreaOfWork areaOfWork;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
