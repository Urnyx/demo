package com.example.demo.dto.mensaje;

import com.example.demo.modelo.Mensaje;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MensajeMapper {

    MensajeMapper INSTANCE = Mappers.getMapper(MensajeMapper.class);

    Mensaje mensajeDtoToMensaje(MensajeDto mensajeDto);

    MensajeDto mensajeToMensajeDto(Mensaje mensaje);

}
