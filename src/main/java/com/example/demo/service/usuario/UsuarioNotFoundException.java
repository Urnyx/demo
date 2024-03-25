package com.example.demo.service.usuario;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(){
        super();
    }

    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
