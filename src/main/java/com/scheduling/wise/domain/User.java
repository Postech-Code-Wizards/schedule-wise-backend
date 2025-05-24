package com.scheduling.wise.domain;

import com.scheduling.wise.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private UserType userType;
    private boolean active;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime deletedAt;

    public User(Long id) {
        this.id = id;
    }

    public void activateUser(boolean active) {
        this.active = active;
    }
}
