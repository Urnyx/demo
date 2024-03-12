package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modelo.Mensaje;
import com.example.demo.repository.MensajeRepository;

public class MensajeService{
    

@Autowired
    private MensajeRepository mensajeRepository;

    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeRepository.findAll();
    }

    public Optional<Mensaje> buscarMensajePorId(Integer id) {
        return mensajeRepository.findById(id);
    }

    public Mensaje guardarMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public void eliminarMensaje(Integer id) {
        mensajeRepository.deleteById(id);
    }
}
