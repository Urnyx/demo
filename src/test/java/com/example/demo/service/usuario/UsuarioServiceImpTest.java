package com.example.demo.service.usuario;

import com.example.demo.dto.usuario.UsuarioDto;
import com.example.demo.dto.usuario.UsuarioMapper;
import com.example.demo.dto.usuario.UsuarioToSaveDto;
import com.example.demo.modelo.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImpTest {
    @Mock
    UsuarioRepository usuarioRepository;
    @Mock
    UsuarioMapper usuarioMapper;
    @InjectMocks
    UsuarioServiceImp usuarioService;
    Usuario usuario;
    Usuario usuario2;
    @BeforeEach
    void setUp() {
         usuario = Usuario.builder()
                .id(1l)
                .userName("Daniel123")
                .email("@daniel")
                .nombre("Daniel")
                .apellido("Pinilla")
                .edad(20)
                .password("password")
                .build();

        usuario2 = Usuario.builder()
                .id(1l)
                .userName("Daniel123")
                .email("@daniel")
                .nombre("Daniel")
                .apellido("Pinilla")
                .edad(20)
                .password("password")
                .build();
    }

    @Test
    void givenUsuario_whenGuardarUsuario_ThenReturnUsuarioDtoGuardado(){
        given(usuarioRepository.save(any())).willReturn(usuario);
        UsuarioDto usuarioDto = new UsuarioDto(
                        1l,
                        "Daniel123",
                        "@daniel",
                        "Daniel",
                        "Pinilla",
                        20,
                        "password",
                        "password",
                        true,
                        "foto",
                        "rol",
                        LocalDateTime.now());

        UsuarioToSaveDto usuarioToSaveDto = new UsuarioToSaveDto(
                1l,
                "Daniel123",
                "@daniel",
                "Daniel",
                "Pinilla",
                20,
                "password",
                "password",
                true,
                "foto",
                "rol",
                LocalDateTime.now());

        given(usuarioMapper.usuarioToUsuarioDto(usuario)).willReturn(usuarioDto);

        UsuarioDto usuario1 = usuarioService.guardarUsuario(usuarioToSaveDto);

        assertThat(usuario1).isNotNull();
        assertThat(usuario1.id()).isEqualTo(1l);
    }

    @Test
    void givenUsuario_whenBuscarUsuario_thenReturnUsuarioDto(){
        Long usuarioid = 1l;
        given(usuarioRepository.findById(usuarioid)).willReturn(Optional.ofNullable(usuario));

        given(usuarioMapper.usuarioToUsuarioDto(usuario)).willReturn(new UsuarioDto(
                1l,
                "Daniel123",
                "@daniel",
                "Daniel",
                "Pinilla",
                20,
                "password",
                "password",
                true,
                "foto",
                "rol",
                LocalDateTime.now()));

        UsuarioDto usuarioDto = usuarioService.buscarUsuarioById(usuarioid);

        assertThat(usuarioDto).isNotNull();
    }

    @Test
    void givenUsuario_whenBuscarUsuario_thenUsuarioNotFoundException(){
        given(usuarioRepository.findById(any())).willReturn(Optional.ofNullable(null));

        Assertions.assertThrows(UsuarioNotFoundException.class,()->{
            usuarioService.buscarUsuarioById(any());
        },"Usuario no encontrado");
    }

    @Test
    void givenUsuario_whenBuscarUsuarioByEmail_thenReturnUsuarioDto() {
        String usuarioEmail = "@Daniel";
        given(usuarioRepository.findByEmail(usuarioEmail)).willReturn(Optional.ofNullable(usuario));

        given(usuarioMapper.usuarioToUsuarioDto(usuario)).willReturn(new UsuarioDto(
                1l,
                "Daniel123",
                "@daniel",
                "Daniel",
                "Pinilla",
                20,
                "password",
                "password",
                true,
                "foto",
                "rol",
                LocalDateTime.now()));

        UsuarioDto usuarioDto = usuarioService.buscarUsuarioByEmail(usuarioEmail);

        assertThat(usuarioDto).isNotNull();
        assertThat(usuarioDto.email()).isEqualTo("@daniel");
    }

    @Test
    void givenUsuario_whenActualizarUsuario_thenReturnUsuarioDto(){
        Long usuarioId = 1l;
        given(usuarioRepository.save(any())).willReturn(usuario);

        given(usuarioRepository.findById(usuarioId)).willReturn(Optional.ofNullable(usuario));

        UsuarioDto usuarioDto = new UsuarioDto(
                1l,
                "Daniel123",
                "@daniel",
                "Andres",
                "Pinilla",
                20,
                "password",
                "password",
                true,
                "foto",
                "rol",
                LocalDateTime.now());

        given(usuarioMapper.usuarioToUsuarioDto(usuario)).willReturn(usuarioDto);

        UsuarioDto usuarioSave = usuarioService.actualizarUsuario(usuarioId,new UsuarioToSaveDto(
                1l,
                "Daniel123",
                "@daniel",
                "Daniel",
                "Pinilla",
                20,
                "password",
                "password",
                true,
                "foto",
                "rol",
                LocalDateTime.now()));

        assertThat(usuarioSave).isNotNull();
        assertThat(usuarioSave.nombre()).isEqualTo("Andres");

    }

    @Test
    void givenUsuario_whenBuscarTodosLosUsuarios_thenReturnAllUsuarios(){
        given(usuarioRepository.findAll()).willReturn(List.of(usuario,usuario2));

        given(usuarioMapper.usuarioToUsuarioDto(usuario)).willReturn(new UsuarioDto(
                1l,
                "Daniel123",
                "@daniel",
                "Andres",
                "Pinilla",
                20,
                "password",
                "password",
                true,
                "foto",
                "rol",
                LocalDateTime.now()));

        List<UsuarioDto> usuarioDtos = usuarioService.obtenerTodosLosUsuarios();

        assertThat(usuarioDtos).isNotNull();
        assertThat(usuarioDtos).hasSize(2);
    }

    @Test
    void givenUsuario_whenDeleteUsuarioById(){
        Long mensajeId = 1L;

        given(usuarioRepository.findById(mensajeId)).willReturn(Optional.of(usuario));

        usuarioService.deleteUsuarioById(mensajeId);

        verify(usuarioRepository, times(1)).deleteById(mensajeId);
    }
}