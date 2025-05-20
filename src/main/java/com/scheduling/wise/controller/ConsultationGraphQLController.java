package com.scheduling.wise.controller;

import com.scheduling.wise.application.service.ConsultationService;
import com.scheduling.wise.controller.input.CreateConsultationInput;
import com.scheduling.wise.controller.input.UpdateConsultationInput;
import com.scheduling.wise.domain.Consultation;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ConsultationGraphQLController {

    private final ConsultationService service;

    public ConsultationGraphQLController(ConsultationService service) {
        this.service = service;
    }

    @QueryMapping
    public Consultation getConsultationById(@Argument Long id) {
        return service.findById(id).orElseThrow();
    }

    @QueryMapping
    public List<Consultation> listConsultations() {
        return service.findAll();
    }

    @MutationMapping
    public Consultation createConsultation(@Argument CreateConsultationInput input) {
        return service.createFromInput(input);
    }

    @MutationMapping
    public Consultation updateConsultation(@Argument UpdateConsultationInput input) {
        return service.updateFromInput(input);
    }

    @MutationMapping
    public Boolean deleteConsultation(@Argument Long id) {
        service.delete(id);
        return true;
    }
}