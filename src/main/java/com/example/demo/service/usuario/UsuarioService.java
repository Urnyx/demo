package com.example.demo.service.usuario;

import com.example.demo.dto.usuario.UsuarioDto;
import com.example.demo.dto.usuario.UsuarioToSaveDto;

import java.util.List;

public interface UsuarioService {

    UsuarioDto guardarUsuario(UsuarioToSaveDto usuarioToSaveDto);
    UsuarioDto buscarUsuarioById(Long id);
    UsuarioDto buscarUsuarioByEmail(String email);
    UsuarioDto actualizarUsuario(Long id, UsuarioToSaveDto usuarioToSaveDto);

    List<UsuarioDto> obtenerTodosLosUsuarios();
    void deleteUsuarioById(Long id);
}
