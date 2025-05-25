package com.scheduling.wise.usecase.user;

import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.UserGateway;
import com.scheduling.wise.gateway.database.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserGateway userGateway;

    public UserEntity execute(User user) {
        user.activateUser(true);
        return userGateway.save(user);
    }
}
