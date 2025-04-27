package com.scheduling.wise.gateway.database.entities;

import com.scheduling.wise.domain.enums.PhoneType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Entity
@Table(name = "phones")
@Getter
@Setter
@AllArgsConstructor
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "area_code", nullable = false, length = 3)
    private String ddd;

    @Column(name = "phone_number", nullable = false, length = 55)
    private String phoneNumber;

    @Column(name = "phone_type", nullable = false)
    private PhoneType phoneType;

    @Column(nullable = false, length = 50)
    private String operator;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
