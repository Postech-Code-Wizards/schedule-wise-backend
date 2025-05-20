package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.DoctorConverter;
import com.scheduling.wise.converter.PhoneConverter;
import com.scheduling.wise.converter.UserConverter;
import com.scheduling.wise.domain.Doctor;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.DoctorRequest;
import com.scheduling.wise.domain.dtos.request.PhoneRequest;
import com.scheduling.wise.domain.dtos.request.UserRequest;
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

    private final DoctorConverter doctorConverter;
    private final UserConverter userConverter;
    private final PhoneConverter phoneConverter;

    @MutationMapping
    public void createDoctor(@Argument("input") DoctorRequest doctorRequest,
                             @Argument("userInput") UserRequest userRequest,
                             @Argument("phoneInput") PhoneRequest phoneRequest) {
        var doctor = doctorConverter.toDomain(doctorRequest);
        var user = userConverter.toDomain(userRequest);
        var phone = phoneConverter.toDomain(phoneRequest);
        createDoctorUseCase.execute(doctor, user, phone);
    }

    @QueryMapping
    public DoctorResponse getDoctorById(@Argument("id") Long id) {
        var domain = getDoctorUseCase.execute(id);
        return doctorConverter.toResponse(domain);
    }

    @QueryMapping
    public List<DoctorResponse> getDoctors() {
        var domain = getAllDoctorUseCase.execute();
        return doctorConverter.toResponse(domain);
    }

    @MutationMapping
    public void updateDoctor(@Argument("id") Long id,
                             @Argument("input") DoctorRequest doctorRequest,
                             @Argument("userInput") UserRequest userRequest) {
        Doctor domain = doctorConverter.toDomain(doctorRequest);
        User user = userConverter.toDomain(userRequest);
        updateDoctorUseCase.execute(id, domain, user);
    }

    @MutationMapping
    public void deleteDoctor(@Argument("id") Long id) {
        deleteDoctorUseCase.execute(id);
    }
}
