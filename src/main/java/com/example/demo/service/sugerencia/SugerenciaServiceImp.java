package com.example.demo.service.sugerencia;

import com.example.demo.dto.sugerencia.SugerenciaDto;
import com.example.demo.dto.sugerencia.SugerenciaMapper;
import com.example.demo.dto.sugerencia.SugerenciaToSaveDto;
import com.example.demo.modelo.Sugerencia;
import com.example.demo.repository.SugerenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class SugerenciaServiceImp implements SugerenciaService{

    SugerenciaRepository sugerenciaRepository;
    SugerenciaMapper sugerenciaMapper;
    @Autowired
    public SugerenciaServiceImp(SugerenciaRepository sugerenciaRepository, SugerenciaMapper sugerenciaMapper) {
        this.sugerenciaRepository = sugerenciaRepository;
        this.sugerenciaMapper = sugerenciaMapper;
    }

    @Override
    public SugerenciaDto guardarSugerencia(SugerenciaToSaveDto sugerenciaToSaveDto) {
        Sugerencia sugerencia = sugerenciaMapper.sugerenciaToSaveDtoToSugerencia(sugerenciaToSaveDto);
        Sugerencia sugerenciaSave = sugerenciaRepository.save(sugerencia);
        return sugerenciaMapper.sugerenciaToSugerenciaDto(sugerenciaSave);
    }

    @Override
    public SugerenciaDto buscarSugerenciabyId(Long id) {
        Sugerencia sugerencia = sugerenciaRepository.findById(id).orElseThrow(SugerenciaNotFoundException::new);
        return sugerenciaMapper.sugerenciaToSugerenciaDto(sugerencia);
    }

    @Override
    public SugerenciaDto buscarSugerenciaByCreate_at(LocalDateTime localDateTime) {
        Sugerencia sugerencia = sugerenciaRepository.findByCreate_at(localDateTime).orElseThrow(SugerenciaNotFoundException::new);
        return sugerenciaMapper.sugerenciaToSugerenciaDto(sugerencia);
    }
}
