package com.example.demo.service.partida;

import com.example.demo.dto.partida.PartidaDto;
import com.example.demo.dto.partida.PartidaMapper;
import com.example.demo.dto.partida.PartidaToSaveDto;
import com.example.demo.modelo.Partida;
import com.example.demo.repository.PartidaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PartidaServiceImpTest {

    @Mock
    PartidaRepository partidaRepository;

    @Mock
    PartidaMapper partidaMapper;

    @InjectMocks
    PartidaServiceImp partidaService;

    Partida partida;
    @BeforeEach
    void setUp() {
         partida = Partida.builder()
                .id(1L)
                .creador("Usuario1")
                .deporte("Fútbol")
                .city("Bogotá")
                .provinces("Cundinamarca")
                .date(LocalDateTime.of(2024, 3, 22, 0, 0))
                .horaInicio(LocalDateTime.of(2024, 3, 22, 10, 0))
                .horaFinal(LocalDateTime.of(2024, 3, 22, 12, 0))
                .participantes(10)
                .suplentes(2)
                .comentarios("Partido de prueba")
                .build();

    }

    @Test
    void givenPartida_whenGuardarPartida_thenReturnPartidaDto(){
        given(partidaRepository.save(any())).willReturn(partida);

        PartidaDto partidaDto = new PartidaDto(
                1L,
                "Usuario1",
                "Fútbol",
                "Bogotá",
                "Cundinamarca",
                LocalDateTime.of(2024, 3, 22, 0, 0),
                LocalDateTime.of(2024, 3, 22, 10, 0),
                LocalDateTime.of(2024, 3, 22, 12, 0),
                10,
                2,
                "Partido de prueba"
        );

        PartidaToSaveDto partidaToSaveDto = new PartidaToSaveDto(
                1L,
                "Usuario1",
                "Fútbol",
                "Bogotá",
                "Cundinamarca",
                LocalDateTime.of(2024, 3, 22, 0, 0),
                LocalDateTime.of(2024, 3, 22, 10, 0),
                LocalDateTime.of(2024, 3, 22, 12, 0),
                10,
                2,
                "Partido de prueba"
        );


        given(partidaMapper.partidaToPartidaDto(any())).willReturn(partidaDto);

        PartidaDto partidaDtoSave = partidaService.guardarPartida(partidaToSaveDto);

        assertThat(partidaDtoSave).isNotNull();
        assertThat(partidaDtoSave.id()).isEqualTo(1L);
        assertThat(partidaDtoSave.city()).isEqualTo("Bogotá");
    }

    @Test

    void givenPartida_whenBuscarPartida_thenReturnPartidaDto(){
        Long partidaId=1L;
        given(partidaRepository.findById(any())).willReturn(Optional.of(partida));

        PartidaDto partidaDto = new PartidaDto(
                1L,
                "Usuario1",
                "Fútbol",
                "Bogotá",
                "Cundinamarca",
                LocalDateTime.of(2024, 3, 22, 0, 0),
                LocalDateTime.of(2024, 3, 22, 10, 0),
                LocalDateTime.of(2024, 3, 22, 12, 0),
                10,
                2,
                "Partido de prueba"
        );

        given(partidaMapper.partidaToPartidaDto(any())).willReturn(partidaDto);

        PartidaDto partidaDtoFind = partidaService.buscarPartidaById(partidaId);

        assertThat(partidaDtoFind).isNotNull();

    }

    @Test
    void givenPartida_whenBuscarPartidaByCreador_thenReturnPartidaDto() {
        String creador = "Usuario1";
        given(partidaRepository.findByCreador(creador)).willReturn(Optional.ofNullable(partida));

        PartidaDto partidaDto = new PartidaDto(
                1L,
                "Usuario1",
                "Fútbol",
                "Bogotá",
                "Cundinamarca",
                LocalDateTime.of(2024, 3, 22, 0, 0),
                LocalDateTime.of(2024, 3, 22, 10, 0),
                LocalDateTime.of(2024, 3, 22, 12, 0),
                10,
                2,
                "Partido de prueba"
        );

        given(partidaMapper.partidaToPartidaDto(partida)).willReturn(partidaDto);

        PartidaDto partidaDtoFind = partidaService.buscarPartidaByCreador(creador);

        assertThat(partidaDtoFind).isNotNull();
        assertThat(partidaDtoFind.creador()).isEqualTo("Usuario1");
    }
    @Test
    void givenPartida_whenBuscarPartidaById_thenReturnPartidaNotFoundException(){
        given(partidaRepository.findById(any())).willReturn(Optional.empty());

        Assertions.assertThrows(PartidaNotFoundException.class,()->{
            partidaService.buscarPartidaById(any());
        },"Usuario no encontrado");
    }

    @Test
    void givenPartida_whenActualizarPartida_thenReturnPartidaDto(){
        Long partidaId = 1L;

        given(partidaRepository.save(any())).willReturn(partida);
        given(partidaRepository.findById(partidaId)).willReturn(Optional.ofNullable(partida));

        PartidaDto partidaDto = new PartidaDto(
                1L,
                "Usuario1",
                "Baloncesto",
                "Bogotá",
                "Cundinamarca",
                LocalDateTime.of(2024, 3, 22, 0, 0),
                LocalDateTime.of(2024, 3, 22, 10, 0),
                LocalDateTime.of(2024, 3, 22, 12, 0),
                10,
                2,
                "Partido de prueba"
        );

        given(partidaMapper.partidaToPartidaDto(any())).willReturn(partidaDto);

        PartidaDto partidaDtoActualizada = partidaService.actualizarPartida(partidaId, new PartidaToSaveDto(
                1L,
                "Usuario1",
                "Baloncesto",
                "Bogotá",
                "Cundinamarca",
                LocalDateTime.of(2024, 3, 22, 0, 0),
                LocalDateTime.of(2024, 3, 22, 10, 0),
                LocalDateTime.of(2024, 3, 22, 12, 0),
                10,
                2,
                "Partido de prueba"
        ));

        assertThat(partidaDtoActualizada).isNotNull();
        assertThat(partidaDtoActualizada.deporte()).isEqualTo("Baloncesto");
    }

    @Test
    void givenPartida_whenEliminarPartida(){
        Long partidaId = 1L;

        given(partidaRepository.findById(partidaId)).willReturn(Optional.of(partida));

        partidaService.eliminarPartida(partidaId);

        verify(partidaRepository, times(1)).deleteById(partidaId);
    }
}