package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Sugerencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SugerenciaRepository extends JpaRepository<Sugerencia, Long>{

    Optional<Sugerencia> findByCreate_at(LocalDateTime localDateTime);
}
