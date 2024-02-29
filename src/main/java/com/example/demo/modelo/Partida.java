package com.example.demo.modelo;

import java.time.LocalDateTime;
import java.util.LinkedList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity(name = "partidas")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String creador;
    private String deporte;
    private String city;
    private String provinces;
    private LocalDateTime date;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFinal;
    private Integer participantes;
    private Integer suplentes;
    private String comentarios;

    @ManyToMany
    @JoinTable(name = "partidas_usuarios",
                joinColumns = @JoinColumn(name = "id_partidas"),
                inverseJoinColumns = @JoinColumn(name ="id_usuarios") )
    private LinkedList<Usuario> users;

}
