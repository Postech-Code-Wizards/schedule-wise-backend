package com.scheduling.wise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Symptom {
    private Long id;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
