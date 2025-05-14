package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Consultation;

import java.util.List;

public interface ConsultationGateway {
    void saveConsultation(Consultation consultation);
    List<Consultation> listUserConsultationsByUserId(Long id);
    Consultation getConsultationById(Long id);
    void deleteConsultationById(Long id);
}
