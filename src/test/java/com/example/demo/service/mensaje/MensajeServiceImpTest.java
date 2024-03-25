package com.example.demo.service.mensaje;

import com.example.demo.dto.mensaje.MensajeDto;
import com.example.demo.dto.mensaje.MensajeMapper;
import com.example.demo.dto.mensaje.MensajeToSaveDto;
import com.example.demo.modelo.Mensaje;
import com.example.demo.repository.MensajeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MensajeServiceImpTest {

    @Mock
    private MensajeRepository mensajeRepository;
    @Mock
    private MensajeMapper mensajeMapper;
    @InjectMocks
    private MensajeServiceImp mensajeService;
    private Mensaje mensaje;
    @BeforeEach
    void setUp() {
        mensaje = Mensaje.builder()
                .id(1L)
                .destinatario("otroCliente")
                .creador("cliente")
                .create_at(LocalDateTime.now())
                .contenido("cosas")
                .build();
        mensajeRepository.save(mensaje);
    }

    @Test

    void givenMensaje_whenGuardarMensaje_thenReturnMensajeGuardado() {
        given(mensajeRepository.save((any()))).willReturn(mensaje);

        MensajeToSaveDto mensaje2 = new MensajeToSaveDto(
                1L,
                "otrocliente",
                "cliente",
                LocalDateTime.now(),
                "cosas"
         );

        given(mensajeMapper.mensajeToMensajeDto(any())).willReturn(new MensajeDto(
                1L,
                "otrocliente",
                "cliente",
                LocalDateTime.now(),
                "cosas"
        ));

        MensajeDto mensajeSave = mensajeService.guardarMensaje(mensaje2);
        assertThat(mensajeSave).isNotNull();
        assertThat(mensajeSave.id()).isEqualTo(1L);
        assertThat(mensajeSave.contenido()).isEqualTo("cosas");
        assertThat(mensajeSave.creador()).isEqualTo("cliente");
        assertThat(mensajeSave.destinatario()).isEqualTo("otrocliente");
    }

    @Test
    void givenMensaje_whenBuscarMensaje_thenReturnMensaje(){
        Long mensajeId = 1L;

        given(mensajeRepository.findById(mensajeId)).willReturn(Optional.ofNullable(mensaje));

        given(mensajeMapper.mensajeToMensajeDto(any())).willReturn(new MensajeDto(
                1L,
                "otrocliente",
                "cliente",
                LocalDateTime.now(),
                "cosas"
        ));

        MensajeDto mensajeDto = mensajeService.buscarMensajeById(mensajeId);

        assertThat(mensajeDto).isNotNull();
    }

    @Test
    void givenMesaje_whenBuscarMensaje_thenReturnException(){
        given(mensajeRepository.findById(any())).willReturn(Optional.ofNullable(null));
        
        assertThrows(MensajeNotFoundException.class,()->{
            mensajeService.buscarMensajeById(any());
        },"usuario no encontrado");
    }

    @Test
    void givenMensaje_whenEliminarMensaje_thenNothing(){
        Long mensajeId = 1L;

        given(mensajeRepository.findById(mensajeId)).willReturn(Optional.of(mensaje));

        mensajeService.eliminarMensaje(mensajeId);

        verify(mensajeRepository, times(1)).deleteById(mensajeId);
    }

    @Test
    void givenMensaje_whenActualizarMensaje_thenMensajeActualizado(){
        Long mensajeId = 1L;
        given(mensajeRepository.save(any())).willReturn(mensaje);
        given(mensajeRepository.findById(any())).willReturn(Optional.ofNullable(mensaje));

        given(mensajeMapper.mensajeToMensajeDto(any())).willReturn(new MensajeDto(
                1L,
                "otrocliente",
                "noCliente",
                LocalDateTime.now(),
                "cosas"
        ));

        MensajeDto mensajeDto = mensajeService.actualizarMensaje(mensajeId,new MensajeToSaveDto(
                1L,
                "otrocliente",
                "noCliente",
                LocalDateTime.now(),
                "cosas"
        ));

        assertThat(mensajeDto).isNotNull();
        assertThat(mensajeDto.id()).isEqualTo(mensajeId);
        assertThat(mensajeDto.creador()).isEqualTo("noCliente");
    }

}