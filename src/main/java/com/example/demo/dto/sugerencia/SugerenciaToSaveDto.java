package com.example.demo.dto.sugerencia;

import java.time.LocalDateTime;

public record SugerenciaToSaveDto(Long id,
                                  String descripcion,
                                  LocalDateTime create_at) {
}
