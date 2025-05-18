package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.UserConverter;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.UserRequest;
import com.scheduling.wise.domain.dtos.response.UserResponse;
import com.scheduling.wise.usecase.user.CreateUserUseCase;
import com.scheduling.wise.usecase.user.DeleteUserUseCase;
import com.scheduling.wise.usecase.user.FindUserUseCase;
import com.scheduling.wise.usecase.user.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserResolver {
    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserConverter converter;

    @MutationMapping
    public void createUser(@Argument("input") UserRequest userRequest) {
        createUserUseCase.execute(converter.toDomain(userRequest));
    }

    @QueryMapping
    public UserResponse getUserById(@Argument("id") Long id) {
        User user = findUserUseCase.execute(id);
        return converter.toResponse(user);
    }

    @MutationMapping
    public void updateUser(@Argument("id") Long id,
                           @Argument("input") UserRequest userRequest) {
        User user = converter.toDomain(userRequest);
        updateUserUseCase.execute(id, user);
    }

    @MutationMapping
    public void deleteUser(@Argument("id") Long id) {
        deleteUserUseCase.execute(id);
    }
}
