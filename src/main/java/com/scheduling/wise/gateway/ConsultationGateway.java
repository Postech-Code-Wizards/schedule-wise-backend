package com.scheduling.wise.gateway;

import com.scheduling.wise.domain.Consultation;

import java.util.List;

public interface ConsultationGateway {
    void save(Consultation consultation);

    List<Consultation> getAllById(Long id);

    Consultation getById(Long id);

    void update(Long id, Consultation consultation);

    void delete(Long id);
}
