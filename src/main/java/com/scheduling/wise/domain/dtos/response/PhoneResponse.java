package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class PhoneResponse {
    Long id;
    String areaCode;
    String phoneNumber;
    PhoneType phoneType;
    String operator;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;

    public PhoneResponse(Long id) {
        this.id = id;
    }
}
