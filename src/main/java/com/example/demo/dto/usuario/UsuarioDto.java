package com.example.demo.dto.usuario;

import com.example.demo.dto.mensaje.MensajeDto;
import com.example.demo.dto.partida.PartidaDto;
import com.example.demo.dto.sugerencia.SugerenciaDto;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioDto(Long id,
                         String userName,
                         String email,
                         String nombre,
                         String apellido,
                         Integer edad,
                         String password,
                         String rep_password,
                         Boolean enable,
                         String foto,
                         String rol,
                         LocalDateTime create_at) {
}
