package com.example.demo.dto.usuario;

import com.example.demo.modelo.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioToSaveDtoToUsuario(UsuarioToSaveDto usuarioToSaveDto);
}
