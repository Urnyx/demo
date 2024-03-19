package com.example.demo.dto.partida;

import com.example.demo.modelo.Partida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PartidaMapper {

    PartidaMapper INSTANCE = Mappers.getMapper(PartidaMapper.class);

    Partida partidaToSaveDtoToPartida(PartidaToSaveDto partidaToSaveDto);

    PartidaDto partidaToPartidaDto(Partida partida);
}
