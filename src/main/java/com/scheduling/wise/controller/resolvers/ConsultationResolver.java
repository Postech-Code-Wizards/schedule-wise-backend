package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.dtos.request.RegisterConsultationRequest;
import com.scheduling.wise.dtos.response.ListConsultationResponse;
import com.scheduling.wise.mappers.ConsultationRequestMapper;
import com.scheduling.wise.usecase.usecases.consultation.CreateConsultationUseCase;
import com.scheduling.wise.usecase.usecases.consultation.ListUserConsultationsUseCase;
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
    private final ListUserConsultationsUseCase listUserConsultationsUseCase;

    private final ConsultationRequestMapper consultationRequestMapper;

    @MutationMapping
    public void createConsultation(@Argument("input") RegisterConsultationRequest request) {
        Consultation consultation = consultationRequestMapper.toConsultation(request);
        createConsultationUseCase.execute(consultation);
    }

    @QueryMapping
    public List<ListConsultationResponse> getConsultations(@Argument("id") Long id) {
        return listUserConsultationsUseCase.execute(id);
    }
}
