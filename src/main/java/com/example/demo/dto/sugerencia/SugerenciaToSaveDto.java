package com.example.demo.dto.sugerencia;

import com.example.demo.modelo.Usuario;

import java.time.LocalDateTime;

public record SugerenciaToSaveDto(Long id,
                                  String descripcion,
                                  LocalDateTime create_at,
                                  Usuario user) {
}
