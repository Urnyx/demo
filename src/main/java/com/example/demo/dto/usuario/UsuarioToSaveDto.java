package com.example.demo.dto.usuario;


import java.time.LocalDateTime;

public record UsuarioToSaveDto(Long id,
                               String userName,
                               String email,
                               String nombre,
                               String apellido,
                               Integer edad,
                               String password,
                               String rep_password,
                               Boolean enable,
                               String foto,
                               String rol,
                               LocalDateTime create_at) {
}
