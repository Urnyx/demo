package com.example.demo.repository;

import com.example.demo.modelo.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Partida;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida,Long>{
}
