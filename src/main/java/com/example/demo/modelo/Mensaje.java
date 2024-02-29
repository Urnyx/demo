package com.example.demo.modelo;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "mensajes")
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String creador;
    private String destinatario;
    private LocalDateTime create_at;
    private String contenido;

    @ManyToOne
    private Usuario user;
}
