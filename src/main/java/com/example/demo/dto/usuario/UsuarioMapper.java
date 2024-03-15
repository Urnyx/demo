package com.example.demo.dto.usuario;

import com.example.demo.modelo.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
}
