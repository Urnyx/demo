package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Sugerencia;

public interface SugerenciaRepository extends JpaRepository<Sugerencia, Integer>{
    
}
