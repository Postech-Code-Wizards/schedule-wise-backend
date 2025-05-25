package com.scheduling.wise.domain.dtos.request;

import com.scheduling.wise.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    Long id;
    String email;
    String password;
    UserType userType;
    boolean isActive;

}
