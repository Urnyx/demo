package com.example.demo.dto.partida;

import com.example.demo.dto.usuario.UsuarioDto;

import java.time.LocalDateTime;
import java.util.List;

public record PartidaDto(Long id,
                         String creador,
                         String deporte,
                         String city,
                         String provinces,
                         LocalDateTime date,
                         LocalDateTime horaInicio,
                         LocalDateTime horaFinal,
                         Integer participantes,
                         Integer suplentes,
                         String comentarios) {
}
