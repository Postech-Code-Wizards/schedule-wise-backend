package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Consultation;

import java.util.List;

public interface ConsultationGateway {
    Consultation save(Consultation consultation);

    List<Consultation> getAllFutureConsultationsById(Long id);

    List<Consultation> getAllConsultationsByPatientId(Long id);

    Consultation getById(Long id);

    Consultation updateCompletion(Long id, Consultation consultation);

    void updateStatus(Long id, Consultation consultation);

    void delete(Long id);
}
