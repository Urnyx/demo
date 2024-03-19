package com.example.demo.dto.partida;

import com.example.demo.modelo.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record PartidaToSaveDto(Long id,
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
