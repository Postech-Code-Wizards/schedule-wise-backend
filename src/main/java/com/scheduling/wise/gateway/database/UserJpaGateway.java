package com.scheduling.wise.gateway.database;

import com.scheduling.wise.converter.UserConverter;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.gateway.UserGateway;
import com.scheduling.wise.gateway.database.entities.UserEntity;
import com.scheduling.wise.gateway.database.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserJpaGateway implements UserGateway {
    private final UserRepository userRepository;
    private final UserConverter converter;

    @Override
    public UserEntity save(User user) {
        return userRepository.save(converter.toEntity(user));
    }

    @Override
    public User getById(Long id) {
        return converter.toDomain(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id " + id)));
    }

    @Override
    public void update(Long id, User newUser) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User no found for id " + id));
        UserEntity newUserEntity = converter.toEntity(newUser);
        user.setEmail(newUserEntity.getEmail());
        user.setPassword(newUserEntity.getPassword());
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(
                converter.toEntity(getById(id)));
    }
}
