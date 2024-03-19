package com.example.demo.service.sugerencia;

public class SugerenciaNotFoundException extends RuntimeException{
    public SugerenciaNotFoundException() {
        super();
    }

    public SugerenciaNotFoundException(String message) {
        super(message);
    }
}
