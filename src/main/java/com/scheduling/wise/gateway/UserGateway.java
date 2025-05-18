package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.User;

public interface UserGateway {
    void save(User user);

    User getById(Long id);

    void update(Long id, User user);

    void delete(Long id);
}
