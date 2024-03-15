package com.example.demo.dto.sugerencia;

import com.example.demo.modelo.Sugerencia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SugerenciaMapper {
    SugerenciaMapper INSTANCE = Mappers.getMapper(SugerenciaMapper.class);

    Sugerencia sugerenciaDtoToSugerencia(SugerenciaDto sugerenciaDto);

    SugerenciaDto sugerenciaToSugerenciaDto(Sugerencia sugerencia);
}
