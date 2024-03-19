package com.example.demo.repository;

import com.example.demo.modelo.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Partida;

import java.util.List;
import java.util.Optional;

public interface PartidaRepository extends JpaRepository<Partida,Long>{
    Optional<Partida> findByCreador(String creador);
}
