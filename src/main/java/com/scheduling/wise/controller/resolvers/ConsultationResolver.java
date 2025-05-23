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
    private final GetAllFutureConsultationsConsultationUseCase getAllFutureConsultationsConsultationUseCase;
    private final UpdateConsultationCompletionUseCase updateConsultationCompletionUseCase;
    private final UpdateConsultationStatusUseCase updateConsultationStatusUseCase;
    private final DeleteConsultationUseCase deleteConsultationUseCase;

    private final ConsultationConverter consultationConverter;

    @MutationMapping
    public void createConsultation(@Argument("input") ConsultationRequest consultationRequest) {
        createConsultationUseCase.execute(consultationConverter.toDomain(consultationRequest));
    }

    @QueryMapping
    public ConsultationResponse getConsultationById(@Argument("id") Long id) {
        var domain = getConsultationUseCase.execute(id);
        return consultationConverter.toResponse(domain);
    }

    @QueryMapping
    public List<ConsultationResponse> getFutureConsultationsByCustomerId(@Argument("id") Long id) {
        var domain = getAllFutureConsultationsConsultationUseCase.execute(id);
        return consultationConverter.toResponse(domain);
    }

    @MutationMapping
    public void updateConsultationCompletion(@Argument("id") Long id,
                                             @Argument("input") ConsultationRequest consultationRequest) {
        Consultation consultation = consultationConverter.toDomain(consultationRequest);
        updateConsultationCompletionUseCase.execute(id, consultation);
    }

    @MutationMapping
    public void updateConsultationStatus(@Argument("id") Long id,
                                         @Argument("input") ConsultationRequest consultationRequest) {
        Consultation consultation = consultationConverter.toDomain(consultationRequest);
        updateConsultationStatusUseCase.execute(id, consultation);
    }

    @MutationMapping
    public void deleteConsultation(@Argument("id") Long id) {
        deleteConsultationUseCase.execute(id);
    }
}
