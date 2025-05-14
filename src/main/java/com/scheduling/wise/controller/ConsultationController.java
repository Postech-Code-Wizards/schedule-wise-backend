package com.scheduling.wise.controller;

import com.scheduling.wise.mappers.ConsultationRequestMapper;
import com.scheduling.wise.domain.Consultation;
import com.scheduling.wise.dtos.request.RegisterConsultationRequest;
import com.scheduling.wise.usecase.usecases.consultation.CreateConsultationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/schedule")
@RequiredArgsConstructor
public class ConsultationController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultationController.class);
    private final CreateConsultationUseCase createConsultationUseCase;
    private final ConsultationRequestMapper consultationRequestMapper;

    @PostMapping
    public ResponseEntity<Void> createPatientSchedule(
            @RequestBody @Valid RegisterConsultationRequest request
    ){
        Consultation consultation = consultationRequestMapper.toConsultation(request);
        createConsultationUseCase.execute(consultation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
