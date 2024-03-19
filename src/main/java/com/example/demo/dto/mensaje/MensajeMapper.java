package com.example.demo.dto.mensaje;

import com.example.demo.modelo.Mensaje;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MensajeMapper {

    MensajeMapper INSTANCE = Mappers.getMapper(MensajeMapper.class);

    Mensaje mensajeToSaveDtoToMensaje(MensajeToSaveDto mensajeToSaveDto);

    MensajeDto mensajeToMensajeDto(Mensaje mensaje);
}
