package com.scheduling.wise.converter;

import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.UserRequest;
import com.scheduling.wise.domain.dtos.response.UserResponse;
import com.scheduling.wise.gateway.database.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public User toDomain(UserRequest request) {
        if (request == null) return null;
        return new User(
                request.getId(),
                request.getEmail(),
                request.getPassword(),
                request.getUserType(),
                request.isActive(),
                null,
                null,
                null
        );
    }

    public UserResponse toResponse(User user) {
        if (user == null) return null;
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getUserType(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt()
        );
    }

    public UserEntity toEntity(User user) {
        if (user == null) return null;
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .userType(user.getUserType())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .build();
    }

    public List<User> toDomain(List<UserEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
