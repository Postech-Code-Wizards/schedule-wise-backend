package com.scheduling.wise.exceptions;

public class ConsultationNotFoundException extends RuntimeException {
    public ConsultationNotFoundException(String message) {
        super(message);
    }
}
