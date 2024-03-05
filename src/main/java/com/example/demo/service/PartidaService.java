package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modelo.Partida;
import com.example.demo.repository.PartidaRepository;

public class PartidaService {
    @Autowired
    private PartidaRepository partidaRepository;

    public List<Partida> obtenerTodasLasPartidas() {
        return partidaRepository.findAll();
    }

    public Optional<Partida> buscarPartidaPorId(Integer id) {
        return partidaRepository.findById(id);
    }

    public Partida guardarPartida(Partida partida) {
        return partidaRepository.save(partida);
    }

    public void eliminarPartida(Integer id) {
        partidaRepository.deleteById(id);
    }
}
