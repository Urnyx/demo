package com.example.demo.repository;

import com.example.demo.modelo.Usuario;
import  static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


class UsuarioRepositoryTest extends AbstractDBTest {

    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    void usuarios(){
        Usuario usuario1 = Usuario.builder()
                .id(1L)
                .edad(20)
                .nombre("Daniel")
                .apellido("Pinilla")
                .foto("una foto")
                .rol("programador")
                .create_at(LocalDateTime.now())
                .build();

        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .edad(20)
                .nombre("Cristian")
                .apellido("Zuñiga")
                .foto("una foto")
                .rol("programador")
                .create_at(LocalDateTime.now())
                .build();

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
    }

    @Test
    void guardarUsuario(){
        Usuario usuario1 = Usuario.builder()
                .id(1L)
                .edad(20)
                .nombre("Daniel")
                .apellido("Pinilla")
                .foto("una foto")
                .rol("programador")
                .create_at(LocalDateTime.now())
                .build();
        usuarioRepository.save(usuario1);
        usuarioRepository.flush();
        assertThat(usuario1.getId()).isNotNull();
        assertThat(usuario1).isNotNull();
    }

    @Test
    void buscarTodosLosUsuarios(){
        usuarios();
        List<Usuario> usuarios = usuarioRepository.findAll();
        usuarioRepository.flush();
        assertThat(usuarios).hasSize(2);
    }

    @Test
    void buscarUsuariosPorId(){
        usuarios();
        Optional<Usuario> usuario = usuarioRepository.findById(2L);
        usuarioRepository.flush();
        assertThat(usuario.isPresent()).isTrue();
        assertThat(usuario.get().getId()).isEqualTo(2L);
    }

    @Test
    void eliminarUsuarioPorId(){
        usuarios();
        usuarioRepository.deleteById(2L);
        List<Usuario> usuarios = usuarioRepository.findAll();
        usuarioRepository.flush();
        assertThat(usuarios).hasSize(1);
    }
}