package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.PatientConverter;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.dtos.request.PatientRequest;
import com.scheduling.wise.domain.dtos.response.PatientResponse;
import com.scheduling.wise.usecase.patient.*;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientResolver {
    private final CreatePatientUseCase createPatientUseCase;
    private final GetPatientUseCase getPatientUseCase;
    private final GetAllPatientUseCase getAllPatientUseCase;
    private final UpdatePatientUseCase updatePatientUseCase;
    private final DeletePatientUseCase deletePatientUseCase;

    private final PatientConverter converter;

    @MutationMapping
    public void createPatient(@Argument("input") PatientRequest patientRequest) {
        createPatientUseCase.execute(converter.toDomain(patientRequest));
    }

    @QueryMapping
    public PatientResponse getPatientById(@Argument("id") Long id) {
        var domain = getPatientUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<PatientResponse> getPatients() {
        var domain = getAllPatientUseCase.execute();
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updatePatient(@Argument("id") Long id,
                              @Argument("input") PatientRequest patientRequest) {
        Patient domain = converter.toDomain(patientRequest);
        updatePatientUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deletePatient(@Argument("id") Long id) {
        deletePatientUseCase.execute(id);
    }
}
