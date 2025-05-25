package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.EmergencyContactConverter;
import com.scheduling.wise.converter.PatientConverter;
import com.scheduling.wise.converter.PhoneConverter;
import com.scheduling.wise.converter.UserConverter;
import com.scheduling.wise.domain.Patient;
import com.scheduling.wise.domain.User;
import com.scheduling.wise.domain.dtos.request.EmergencyContactRequest;
import com.scheduling.wise.domain.dtos.request.PatientRequest;
import com.scheduling.wise.domain.dtos.request.PhoneRequest;
import com.scheduling.wise.domain.dtos.request.UserRequest;
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

    private final PatientConverter patientConverter;
    private final UserConverter userConverter;
    private final EmergencyContactConverter emergencyContactConverter;
    private final PhoneConverter phoneConverter;

    @MutationMapping
    public void createPatient(@Argument("input") PatientRequest patientRequest,
                              @Argument("userInput") UserRequest userRequest,
                              @Argument("emergencyContactInput") EmergencyContactRequest emergencyContactRequest,
                              @Argument("phoneInput") PhoneRequest patientPhoneRequest,
                              @Argument("emergencyContactPhone") PhoneRequest emergencyContactPhoneRequest) {
        var user = userConverter.toDomain(userRequest);
        var patient = patientConverter.toDomain(patientRequest);
        var emergencyContact = emergencyContactConverter.toDomain(emergencyContactRequest);
        var patientPhone = phoneConverter.toDomain(patientPhoneRequest);
        var emergencyContactPhone = phoneConverter.toDomain(emergencyContactPhoneRequest);
        createPatientUseCase.execute(patient, user, emergencyContact, patientPhone, emergencyContactPhone);
    }

    @QueryMapping
    public PatientResponse getPatientById(@Argument("id") Long id) {
        var domain = getPatientUseCase.execute(id);
        return patientConverter.toResponse(domain);
    }

    @QueryMapping
    public List<PatientResponse> getPatients() {
        var domain = getAllPatientUseCase.execute();
        return patientConverter.toResponse(domain);
    }

    @MutationMapping
    public void updatePatient(@Argument("id") Long id,
                              @Argument("userInput") PatientRequest patientRequest,
                              @Argument("emergencyContactInput") UserRequest userRequest) {
        Patient domain = patientConverter.toDomain(patientRequest);
        User user = userConverter.toDomain(userRequest);
        updatePatientUseCase.execute(id, domain, user);
    }

    @MutationMapping
    public void deletePatient(@Argument("id") Long id) {
        deletePatientUseCase.execute(id);
    }
}
