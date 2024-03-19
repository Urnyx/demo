package com.example.demo.service.mensaje;

public class MensajeNotFoundException extends RuntimeException{
    public MensajeNotFoundException() {
        super();
    }

    public MensajeNotFoundException(String message) {
        super(message);
    }
}
