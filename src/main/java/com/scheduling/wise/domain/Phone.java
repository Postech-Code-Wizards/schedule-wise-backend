package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Builder
@Getter
@AllArgsConstructor
public class Phone {
    private Long id;
    private String areaCode;
    private String phoneNumber;
    private PhoneType phoneType;
    private String operator;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
