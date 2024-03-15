package com.example.demo.dto.usuario;

import com.example.demo.modelo.Mensaje;
import com.example.demo.modelo.Partida;
import com.example.demo.modelo.Sugerencia;

import java.time.LocalDateTime;
import java.util.List;

public record UsuarioToSaveDto(Long id,
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
                               LocalDateTime create_at,
                               List<Sugerencia> sugerencias,
                               List<Mensaje> mensajes,
                               List<Partida> partidas) {
}
