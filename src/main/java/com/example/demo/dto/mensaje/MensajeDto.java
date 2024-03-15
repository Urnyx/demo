package com.example.demo.dto.mensaje;

import com.example.demo.dto.usuario.UsuarioDto;

import java.time.LocalDateTime;

public record MensajeDto(Long id,
                         String destinatario,
                         String creador,
                         LocalDateTime create_at,
                         String contenido,
                         UsuarioDto user) {
}
