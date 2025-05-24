package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    private Long id;
    private String areaCode;
    private String phoneNumber;
    private PhoneType phoneType;
    private String operator;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Phone(Long id){
        this.id = id;
    }
}
