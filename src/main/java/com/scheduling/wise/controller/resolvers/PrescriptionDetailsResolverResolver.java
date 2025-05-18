package com.scheduling.wise.controller.resolvers;

import com.scheduling.wise.converter.PrescriptionDetailsConverter;
import com.scheduling.wise.domain.PrescriptionDetails;
import com.scheduling.wise.domain.dtos.request.PrescriptionDetailsRequest;
import com.scheduling.wise.domain.dtos.response.PrescriptionDetailsResponse;
import com.scheduling.wise.usecase.prescriptiondetails.*;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class PrescriptionDetailsResolverResolver {
    private final CreatePrescriptionDetailsCase createPrescriptionDetailsCase;
    private final GetPrescriptionDetailsUseCase getPrescriptionDetailsUseCase;
    private final GetAllPrescriptionDetailsUseCase getAllPrescriptionDetailsUseCase;
    private final UpdatePrescriptionDetailsUseCase updatePrescriptionDetailsUseCase;
    private final DeletePrescriptionDetailsUseCase deletePrescriptionDetailsUseCase;
    private final PrescriptionDetailsConverter converter;

    @MutationMapping
    public void createPrescription(@Argument("input") PrescriptionDetailsRequest prescriptionDetailsRequest) {
        createPrescriptionDetailsCase.execute(converter.toDomain(prescriptionDetailsRequest));
    }

    @QueryMapping
    public PrescriptionDetailsResponse getPrescriptionDetailsById(@Argument("id") Long id) {
        var domain = getPrescriptionDetailsUseCase.execute(id);
        return converter.toResponse(domain);
    }

    @QueryMapping
    public List<PrescriptionDetailsResponse> getAllPrescriptionsDetails() {
        var domain = getAllPrescriptionDetailsUseCase.execute();
        return converter.toResponse(domain);
    }

    @MutationMapping
    public void updatePrescription(@Argument("id") Long id,
                              @Argument("input") PrescriptionDetailsRequest prescriptionDetailsRequest) {
        PrescriptionDetails domain = converter.toDomain(prescriptionDetailsRequest);
        updatePrescriptionDetailsUseCase.execute(id, domain);
    }

    @MutationMapping
    public void deletePrescription(@Argument("id") Long id) {
        deletePrescriptionDetailsUseCase.execute(id);
    }
}
