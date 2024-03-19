package com.example.demo.dto.sugerencia;

import com.example.demo.modelo.Sugerencia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SugerenciaMapper {
    SugerenciaMapper INSTANCE = Mappers.getMapper(SugerenciaMapper.class);

    Sugerencia sugerenciaToSaveDtoToSugerencia(SugerenciaToSaveDto sugerenciaToSaveDto);

    SugerenciaDto sugerenciaToSugerenciaDto(Sugerencia sugerencia);
}
