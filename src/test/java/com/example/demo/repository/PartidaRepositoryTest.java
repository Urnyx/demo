package com.example.demo.repository;

import com.example.demo.modelo.Partida;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PartidaRepositoryTest extends AbstractDBTest{

    PartidaRepository partidaRepository;

    @Autowired
    public PartidaRepositoryTest(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    void partidas() {
        Partida partida = Partida.builder()
                .city("ciudad")
                .comentarios("comentarios")
                .date(LocalDateTime.now())
                .creador("creador")
                .deporte("Deporte")
                .build();

        Partida partida2 = Partida.builder()
                .city("ciudad")
                .comentarios("comentarios")
                .date(LocalDateTime.now())
                .creador("creador")
                .deporte("Deporte")
                .build();

        partidaRepository.save(partida);
        partidaRepository.save(partida2);
    }

    @Test
    void findAllSpy() {
        partidas();
        List<Partida> partidas = partidaRepository.findAll();
        partidaRepository.flush();
        assertThat(partidas).hasSize(2);
    }

    @Test
    void buscarPartidaPorId() {
        partidas();
        Optional<Partida> partida = partidaRepository.findById(1L);
        partidaRepository.flush();
        assertThat(partida.isPresent()).isTrue();
        assertThat(partida.get().getId()).isEqualTo(1L);
    }

    @Test
    void guardarPartida(){
        Partida partida2 = Partida.builder()
                .city("ciudad")
                .comentarios("comentarios")
                .date(LocalDateTime.now())
                .creador("creador")
                .deporte("Deporte")
                .build();
        Partida partida = partidaRepository.save(partida2);
        assertThat(partida).isNotNull();
        assertThat(partida.getId()).isNotNull();
    }

    @Test
    void borrarPartidaPorId(){
        partidas();
        partidaRepository.deleteById(1L);
        List<Partida> partidas = partidaRepository.findAll();
        partidaRepository.flush();
        assertThat(partidas).hasSize(1);
    }
}