package com.scheduling.wise.usecase.user;

import com.scheduling.wise.gateway.UserGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCase {
    private final UserGateway userGateway;

    public void execute(Long id) {
        userGateway.delete(id);
    }
}
