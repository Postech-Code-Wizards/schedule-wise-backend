package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.DoctorConverter;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.dtos.request.DoctorRequest;
import com.scheduling.wise.domain.dtos.response.DoctorResponse;
import com.scheduling.wise.usecase.doctor.*;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DoctorResolver {
    private final CreateDoctorUseCase createDoctorUseCase;
    private final GetDoctorUseCase getDoctorUseCase;
    private final GetAllDoctorUseCase getAllDoctorUseCase;
    private final UpdateDoctorUseCase updateDoctorUseCase;
    private final DeleteDoctorUseCase deleteDoctorUseCase;

    private final DoctorConverter converter;

    @MutationMapping
    public void createDoctor(@Argument("input") DoctorRequest doctorRequest) {
        createDoctorUseCase.execute(converter.toDomain(doctorRequest));
    }

    @QueryMapping
    public DoctorResponse getDoctorById(@Argument("id") Long id) {
        var domain = getDoctorUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<DoctorResponse> getDoctors() {
        var domain = getAllDoctorUseCase.execute();
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updateDoctor(@Argument("id") Long id,
                             @Argument("input") DoctorRequest doctorRequest) {
        Doctor domain = converter.toDomain(doctorRequest);
        updateDoctorUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deleteDoctor(@Argument("id") Long id) {
        deleteDoctorUseCase.execute(id);
    }
}
