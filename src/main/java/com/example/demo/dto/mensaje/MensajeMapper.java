package com.example.demo.dto.mensaje;

import com.example.demo.modelo.Mensaje;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MensajeMapper {
    Mensaje mensajeToSaveDtoToMensaje(MensajeToSaveDto mensajeToSaveDto);

    MensajeDto mensajeToMensajeDto(Mensaje mensaje);
}
