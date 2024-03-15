package com.example.demo.dto.partida;

import com.example.demo.modelo.Partida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartidaMapper {

    PartidaMapper INSTANCE = Mappers.getMapper(PartidaMapper.class);

    Partida partidaDtoToPartida(PartidaDto partidaDto);

    PartidaDto partidaToPartidaDto(Partida partida);
}
