package com.example.demo.service.sugerencia;

import java.time.LocalDateTime;

import com.example.demo.dto.sugerencia.SugerenciaDto;
import com.example.demo.dto.sugerencia.SugerenciaToSaveDto;

public interface SugerenciaService {
    SugerenciaDto guardarSugerencia(SugerenciaToSaveDto sugerenciaToSaveDto);
    SugerenciaDto buscarSugerenciabyId(Long id);
    SugerenciaDto buscarSugerenciaByCreate_at(LocalDateTime localDateTime);
    SugerenciaDto actualizarSugerencia(Long id, SugerenciaToSaveDto sugerenciaToSaveDto);
    void eliminarSugerencia(Long id);
}
