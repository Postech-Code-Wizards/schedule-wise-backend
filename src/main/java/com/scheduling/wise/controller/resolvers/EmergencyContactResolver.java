package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.EmergencyContactConverter;
import com.scheduling.wise.domain.EmergencyContact;
import com.scheduling.wise.domain.dtos.request.EmergencyContactRequest;
import com.scheduling.wise.domain.dtos.response.EmergencyContactResponse;
import com.scheduling.wise.usecase.emergencycontact.*;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmergencyContactResolver {
    private final CreateEmergencyContactUseCase createEmergencyContactUseCase;
    private final GetEmergencyContactUseCase getEmergencyContactUseCase;
    private final GetAllEmergencyContactUseCase getAllEmergencyContactUseCase;
    private final UpdateEmergencyContactUseCase updateEmergencyContactUseCase;
    private final DeleteEmergencyContactUseCase deleteEmergencyContactUseCase;

    private final EmergencyContactConverter converter;

    @MutationMapping
    public void createEmergencyContact(@Argument("input") EmergencyContactRequest emergencyContactRequest) {
        createEmergencyContactUseCase.execute(converter.toDomain(emergencyContactRequest));
    }

    @QueryMapping
    public EmergencyContactResponse getEmergencyContactById(@Argument("id") Long id) {
        var domain = getEmergencyContactUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<EmergencyContactResponse> getEmergencyContacts() {
        var domain = getAllEmergencyContactUseCase.execute();
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updateEmergencyContact(@Argument("id") Long id,
                                       @Argument("input") EmergencyContactRequest emergencyContactRequest) {
        EmergencyContact domain = converter.toDomain(emergencyContactRequest);
        updateEmergencyContactUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deleteEmergencyContact(@Argument("id") Long id) {
        deleteEmergencyContactUseCase.execute(id);
    }

}
