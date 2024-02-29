package com.example.demo.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "sugerencias")
public class Sugerencia {
    private Long id;
    private String descripcion;
    private LocalDateTime create_at;

    @ManyToOne
    private Usuario user;
}
