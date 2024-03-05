package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modelo.Sugerencia;
import com.example.demo.repository.SugerenciaRepository;

public class SugerenciaService {
    
     @Autowired
    private SugerenciaRepository sugerenciaRepository;

    public List<Sugerencia> obtenerTodasLasSugerencias() {
        return sugerenciaRepository.findAll();
    }

    public Optional<Sugerencia> buscarSugerenciaPorId(Integer id) {
        return sugerenciaRepository.findById(id);
    }

    public Sugerencia guardarSugerencia(Sugerencia sugerencia) {
        return sugerenciaRepository.save(sugerencia);
    }

    public void eliminarSugerencia(Integer id) {
        sugerenciaRepository.deleteById(id);
    }
}
