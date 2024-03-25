package com.example.demo.service.sugerencia;

import com.example.demo.dto.sugerencia.SugerenciaDto;
import com.example.demo.dto.sugerencia.SugerenciaMapper;
import com.example.demo.dto.sugerencia.SugerenciaToSaveDto;
import com.example.demo.modelo.Sugerencia;
import com.example.demo.repository.SugerenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SugerenciaServiceImpTest {

    @Mock
    SugerenciaRepository sugerenciaRepository;
    @Mock
    SugerenciaMapper sugerenciaMapper;
    @InjectMocks
    SugerenciaServiceImp sugerenciaServiceImp;

    Sugerencia sugerencia;
    @BeforeEach
    void setUp() {

        sugerencia = Sugerencia.builder()
                .id(1L)
                .descripcion("una sugerencia")
                .create_at(LocalDateTime.now())
                .build();
    }

    @Test
    void givenSugerencia_whenGuardarSugerencia_thenReturnSugerenciaDto() {
        given(sugerenciaRepository.save(any())).willReturn(sugerencia);

        SugerenciaDto sugerenciaDto = new SugerenciaDto(
                1L,
                "Descripción de la sugerencia",
                LocalDateTime.now()
        );

        SugerenciaToSaveDto sugerenciaToSaveDto = new SugerenciaToSaveDto(
                1L,
                "Descripción de la sugerencia",
                LocalDateTime.now()
        );

        given(sugerenciaMapper.sugerenciaToSugerenciaDto(any())).willReturn(sugerenciaDto);

        SugerenciaDto sugerenciaDtoSave = sugerenciaServiceImp.guardarSugerencia(sugerenciaToSaveDto);

        assertThat(sugerenciaDtoSave).isNotNull();
        assertThat(sugerenciaDtoSave.id()).isEqualTo(1L);
        assertThat(sugerenciaDtoSave.descripcion()).isEqualTo("Descripción de la sugerencia");
    }

    @Test
    void givenSugerencia_whenBuscarSugerencia_thenReturnSugerenciaDto(){
        Long sugerenciaId = 1L;

        given(sugerenciaRepository.findById(sugerenciaId)).willReturn(Optional.ofNullable(sugerencia));

        SugerenciaDto sugerenciaDto = new SugerenciaDto(
                1L,
                "Descripción de la sugerencia",
                LocalDateTime.now()
        );
        given(sugerenciaMapper.sugerenciaToSugerenciaDto(sugerencia)).willReturn(sugerenciaDto);
        SugerenciaDto sugerenciaDtoFind = sugerenciaServiceImp.buscarSugerenciabyId(sugerenciaId);

        assertThat(sugerenciaDto).isNotNull();
    }

    @Test
    void givenSugerencia_whenBuscarSugerencia_thenReturnSugerenciaNotFoundException(){
        given(sugerenciaRepository.findById(any())).willReturn(Optional.ofNullable(null));

        assertThrows(SugerenciaNotFoundException.class,()->{
            sugerenciaServiceImp.buscarSugerenciabyId(any());
        },"Sugerencia no encontrada");
    }

    @Test
    void givenFechaCreacion_whenBuscarSugerenciaByCreate_at_thenReturnSugerenciaDto() {
        LocalDateTime fechaCreacion = LocalDateTime.now();

        given(sugerenciaRepository.findByCreate_at(fechaCreacion)).willReturn(Optional.ofNullable(sugerencia));

        SugerenciaDto sugerenciaDto = new SugerenciaDto(
                1L,
                "Descripción de la sugerencia",
                fechaCreacion
        );

        given(sugerenciaMapper.sugerenciaToSugerenciaDto(any())).willReturn(sugerenciaDto);

        SugerenciaDto sugerenciaDtoFound = sugerenciaServiceImp.buscarSugerenciaByCreate_at(fechaCreacion);

        assertThat(sugerenciaDtoFound).isNotNull();
        assertThat(sugerenciaDtoFound.id()).isEqualTo(1L);
        assertThat(sugerenciaDtoFound.descripcion()).isEqualTo("Descripción de la sugerencia");
        assertThat(sugerenciaDtoFound.create_at()).isEqualTo(fechaCreacion);
    }
    @Test
    void givenSugerencia_whenActualizarSugerencia_thenReturnSugerenciaDto() {
        Long sugerenciaId = 1L;
        SugerenciaToSaveDto sugerenciaToSaveDto = new SugerenciaToSaveDto(
                1l,
                "Nueva descripción de la sugerencia",
                LocalDateTime.now()
        );

        given(sugerenciaRepository.findById(sugerenciaId)).willReturn(Optional.of(sugerencia));

        given(sugerenciaRepository.save(any())).willReturn(sugerencia);

        SugerenciaDto sugerenciaDto = new SugerenciaDto(
                1L,
                "Nueva descripción de la sugerencia",
                LocalDateTime.now()
        );

        given(sugerenciaMapper.sugerenciaToSugerenciaDto(sugerencia)).willReturn(sugerenciaDto);

        SugerenciaDto sugerenciaDtoUpdated = sugerenciaServiceImp.actualizarSugerencia(sugerenciaId, sugerenciaToSaveDto);

        assertThat(sugerenciaDtoUpdated).isNotNull();
        assertThat(sugerenciaDtoUpdated.id()).isEqualTo(1L);
        assertThat(sugerenciaDtoUpdated.descripcion()).isEqualTo("Nueva descripción de la sugerencia");

        verify(sugerenciaRepository).findById(sugerenciaId);
        verify(sugerenciaRepository).save(sugerencia);
    }

    @Test
    void givenSugerencia_whenEliminarSugerencia_thenDeleteSugerencia() {
        Long sugerenciaId = 1L;
        given(sugerenciaRepository.findById(sugerenciaId)).willReturn(Optional.of(sugerencia));
        sugerenciaServiceImp.eliminarSugerencia(sugerenciaId);

        verify(sugerenciaRepository, times(1)).deleteById(sugerenciaId);
    }
}