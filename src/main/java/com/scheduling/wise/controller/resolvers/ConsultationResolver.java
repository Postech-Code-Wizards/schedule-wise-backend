package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.ConsultationConverter;
import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.domain.dtos.request.ConsultationRequest;
import com.scheduling.wise.domain.dtos.response.ConsultationResponse;
import com.scheduling.wise.usecase.consultation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConsultationResolver {
    private final CreateConsultationUseCase createConsultationUseCase;
    private final GetConsultationUseCase getConsultationUseCase;
    private final GetAllConsultationUseCase getAllConsultationUseCase;
    private final UpdateConsultationUseCase updateConsultationUseCase;
    private final DeleteConsultationUseCase deleteConsultationUseCase;

    private final ConsultationConverter converter;

    @MutationMapping
    public void createConsultation(@Argument("input") ConsultationRequest consultationRequest) {
        createConsultationUseCase.execute(converter.toDomain(consultationRequest));
    }

    @QueryMapping
    public ConsultationResponse getConsultationById(@Argument("id") Long id) {
        var domain = getConsultationUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<ConsultationResponse> getConsultations(@Argument("id") Long id) {
        var domain = getAllConsultationUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updateConsultation(@Argument("id") Long id,
                                   @Argument("input") ConsultationRequest consultationRequest) {
        Consultation domain = converter.toDomain(consultationRequest);
        updateConsultationUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deleteConsultation(@Argument("id") Long id) {
        deleteConsultationUseCase.execute(id);
    }
}
