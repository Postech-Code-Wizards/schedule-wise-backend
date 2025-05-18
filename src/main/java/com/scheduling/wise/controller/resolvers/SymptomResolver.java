package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.SymptomConverter;
import com.scheduling.wise.domain.Symptom;
import com.scheduling.wise.domain.dtos.request.SymptomRequest;
import com.scheduling.wise.domain.dtos.response.SymptomResponse;
import com.scheduling.wise.usecase.symptom.*;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class SymptomResolver {
    private final CreateSymptomUseCase createSymptomUseCase;
    private final GetSymptomUseCase getSymptomUseCase;
    private final GetSymptomsUseCase getSymptomsUseCase;
    private final UpdateSymptomUseCase updateSymptomUseCase;
    private final DeleteSymptomUseCase deleteSymptomUseCase;
    private final SymptomConverter converter;

    @MutationMapping
    public void createSymptom(@Argument("input") SymptomRequest symptomRequest) {
        createSymptomUseCase.execute(converter.toDomain(symptomRequest));
    }

    @QueryMapping
    public SymptomResponse getSymptomById(@Argument("id") Long id) {
        var domain = getSymptomUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<SymptomResponse> getSymptoms() {
        var domain = getSymptomsUseCase.execute();
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updateSymptom(@Argument("id") Long id,
                              @Argument("input") SymptomRequest symptomRequest) {
        Symptom domain = converter.toDomain(symptomRequest);
        updateSymptomUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deleteSymptom(@Argument("id") Long id) {
        deleteSymptomUseCase.execute(id);
    }
}
