package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.NurseConverter;
import com.scheduling.wise.domain.Nurse;
import com.scheduling.wise.domain.dtos.request.NurseRequest;
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

    private final NurseConverter converter;

    @MutationMapping
    public void createNurse(@Argument("input") NurseRequest nurseRequest) {
        createNurseUseCase.execute(converter.toDomain(nurseRequest));
    }

    @QueryMapping
    public NurseResponse getNurseById(@Argument("id") Long id) {
        var domain = getNurseUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<NurseResponse> getNurses() {
        var domain = getAllNurseUseCase.execute();
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updateNurse(@Argument("id") Long id,
                            @Argument("input") NurseRequest nurseRequest) {
        Nurse domain = converter.toDomain(nurseRequest);
        updateNurseUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deleteNurse(@Argument("id") Long id) {
        deleteNurseUseCase.execute(id);
    }
}
