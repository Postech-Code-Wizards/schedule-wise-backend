package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.NurseConverter;
import com.scheduling.wise.converter.PhoneConverter;
import com.scheduling.wise.converter.UserConverter;
import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.NurseRequest;
import com.scheduling.wise.domain.dtos.request.PhoneRequest;
import com.scheduling.wise.domain.dtos.request.UserRequest;
import com.scheduling.wise.domain.dtos.response.NurseResponse;
import com.scheduling.wise.usecase.nurse.*;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class NurseResolver {
    private final CreateNurseUseCase createNurseUseCase;
    private final GetNurseUseCase getNurseUseCase;
    private final GetAllNurseUseCase getAllNurseUseCase;
    private final UpdateNurseUseCase updateNurseUseCase;
    private final DeleteNurseUseCase deleteNurseUseCase;

    private final NurseConverter nurseConverter;
    private final UserConverter userConverter;
    private final PhoneConverter phoneConverter;

    @MutationMapping
    public void createNurse(@Argument("input") NurseRequest nurseRequest,
                            @Argument("userInput") UserRequest userRequest,
                            @Argument("phoneInput") PhoneRequest phoneRequest) {
        var nurse = nurseConverter.toDomain(nurseRequest);
        var user = userConverter.toDomain(userRequest);
        var phone = phoneConverter.toDomain(phoneRequest);
        createNurseUseCase.execute(nurse, user, phone);
    }

    @QueryMapping
    public NurseResponse getNurseById(@Argument("id") Long id) {
        var domain = getNurseUseCase.execute(id);
        return nurseConverter.toResponse(domain);
    }

    @QueryMapping
    public List<NurseResponse> getNurses() {
        var domain = getAllNurseUseCase.execute();
        return nurseConverter.toResponse(domain);
    }

    @MutationMapping
    public void updateNurse(@Argument("id") Long id,
                            @Argument("input") NurseRequest nurseRequest,
                            @Argument("userInput") UserRequest userRequest) {
        Nurse domain = nurseConverter.toDomain(nurseRequest);
        User user = userConverter.toDomain(userRequest);
        updateNurseUseCase.execute(id, domain, user);
    }

    @MutationMapping
    public void deleteNurse(@Argument("id") Long id) {
        deleteNurseUseCase.execute(id);
    }
}
