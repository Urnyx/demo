package com.example.demo.service.partida;

public class PartidaNotFoundException extends RuntimeException{

    public PartidaNotFoundException() {
        super();
    }

    public PartidaNotFoundException(String message) {
        super(message);
    }
}
