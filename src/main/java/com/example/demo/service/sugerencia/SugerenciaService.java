package com.example.demo.service.sugerencia;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo.dto.sugerencia.SugerenciaDto;
import com.example.demo.dto.sugerencia.SugerenciaToSaveDto;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.modelo.Sugerencia;
import com.example.demo.repository.SugerenciaRepository;

public interface SugerenciaService {
    SugerenciaDto guardarSugerencia(SugerenciaToSaveDto sugerenciaToSaveDto);

    SugerenciaDto buscarSugerenciabyId(Long id);

    SugerenciaDto buscarSugerenciaByCreate_at(LocalDateTime localDateTime);
}
