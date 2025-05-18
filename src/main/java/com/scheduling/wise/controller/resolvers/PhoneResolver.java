package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.PhoneConverter;
import com.scheduling.wise.domain.Phone;
import com.scheduling.wise.domain.dtos.request.PhoneRequest;
import com.scheduling.wise.domain.dtos.response.PhoneResponse;
import com.scheduling.wise.usecase.phone.*;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PhoneResolver {
    private final CreatePhoneUseCase createPhoneUseCase;
    private final GetPhoneUseCase getPhoneUseCase;
    private final GetAllPhoneUseCase getAllPhoneUseCase;
    private final UpdatePhoneUseCase updatePhoneUseCase;
    private final DeletePhoneUseCase deletePhoneUseCase;

    private final PhoneConverter converter;

    @MutationMapping
    public void createPhone(@Argument("input") PhoneRequest phoneRequest) {
        createPhoneUseCase.execute(converter.toDomain(phoneRequest));
    }

    @QueryMapping
    public PhoneResponse getPhoneById(@Argument("id") Long id) {
        var domain = getPhoneUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<PhoneResponse> getPhones() {
        var domain = getAllPhoneUseCase.execute();
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updatePhone(@Argument("id") Long id,
                            @Argument("input") PhoneRequest phoneRequest) {
        Phone domain = converter.toDomain(phoneRequest);
        updatePhoneUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deletePhone(@Argument("id") Long id) {
        deletePhoneUseCase.execute(id);
    }

}
