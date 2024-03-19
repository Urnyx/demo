package com.example.demo.service.usuario;

import com.example.demo.dto.usuario.UsuarioDto;
import com.example.demo.dto.usuario.UsuarioToSaveDto;

public interface UsuarioService {

    UsuarioDto guardarUsuario(UsuarioToSaveDto usuarioToSaveDto);

    UsuarioDto buscarUsuarioById(Long id);

    UsuarioDto buscarUsuarioByEmail(String email);

    void deleteUsuarioById(Long id);
}
