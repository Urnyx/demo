package com.example.demo.modelo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity(name = "sugerencias")
public class Sugerencia {
    private Long id;
    private String descripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_at;

    @ManyToOne
    private Usuario user;
}
