package com.example.demo.dto.sugerencia;

import com.example.demo.dto.usuario.UsuarioDto;

import java.time.LocalDateTime;


public record SugerenciaDto(Long id,
                            String descripcion,
                            LocalDateTime create_at) {
}
