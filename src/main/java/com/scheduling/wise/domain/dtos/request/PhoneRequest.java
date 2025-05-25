package com.scheduling.wise.domain.dtos.request;

import com.scheduling.wise.domain.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneRequest {
    Long id;
    String areaCode;
    String phoneNumber;
    PhoneType phoneType;
    String operator;

}
