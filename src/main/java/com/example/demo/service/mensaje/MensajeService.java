package com.example.demo.service.mensaje;

import com.example.demo.dto.mensaje.MensajeDto;
import com.example.demo.dto.mensaje.MensajeToSaveDto;

public interface MensajeService {

    MensajeDto guardarMensaje(MensajeToSaveDto mensajeToSaveDto);

    MensajeDto buscarMensajeById(Long id);

    MensajeDto buscarMensajeByCreador(String creador);

    void eliminarMensaje(Long id);
}
