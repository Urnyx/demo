package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer>{
    
}
