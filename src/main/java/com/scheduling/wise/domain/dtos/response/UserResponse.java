package com.scheduling.wise.domain.dtos.response;

import com.scheduling.wise.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class UserResponse {
    Long id;
    String email;
    String password;
    UserType userType;
    boolean active;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
    ZonedDateTime deletedAt;

}
