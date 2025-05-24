package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.database.entities.UserEntity;

public interface UserGateway {
    UserEntity save(User user);

    User getById(Long id);

    void update(Long id, User user);

    void delete(Long id);
}
