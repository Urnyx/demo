package com.example.demo.repository;

import com.example.demo.modelo.Sugerencia;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SugerenciaRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>();

    SugerenciaRepository sugerenciaRepository;
    @Autowired
    public SugerenciaRepositoryTest(SugerenciaRepository sugerenciaRepository){
        this.sugerenciaRepository = sugerenciaRepository;
    }

    void sugerencias(){
        Sugerencia sugerencia = Sugerencia.builder()
                .id(1L)
                .descripcion("hola bb")
                .descripcion("adios bb")
                .build();

        Sugerencia sugerencia2 = Sugerencia.builder()
                .id(2L)
                .descripcion("hola bb")
                .descripcion("adios bb")
                .build();

        sugerenciaRepository.save(sugerencia);
        sugerenciaRepository.save(sugerencia2);

    }

    @Test
    void guardarSugerencia(){
        Sugerencia sugerencia2 = Sugerencia.builder()
                .id(2L)
                .descripcion("hola bb")
                .descripcion("adios bb")
                .build();

        Sugerencia sugerencia = sugerenciaRepository.save(sugerencia2);
        sugerenciaRepository.flush();
        assertThat(sugerencia).isNotNull();
        assertThat(sugerencia.getId()).isNotNull();
    }

    @Test
    void buscarTodasLasSugerencias(){
        sugerencias();
        List<Sugerencia> sugerencias = sugerenciaRepository.findAll();
        sugerenciaRepository.flush();
        assertThat(sugerencias).hasSize(2);
    }

    @Test
    void buscarPorId(){
        sugerencias();
        Optional<Sugerencia> sugerencia = sugerenciaRepository.findById(1L);
        sugerenciaRepository.flush();
        assertThat(sugerencia.isPresent()).isTrue();
        assertThat(sugerencia.get().getId()).isEqualTo(1L);
    }

    @Test
    void eliminarPorId(){
        sugerencias();
        sugerenciaRepository.deleteById(1L);
        List<Sugerencia> sugerencias = sugerenciaRepository.findAll();
        sugerenciaRepository.flush();
        assertThat(sugerencias).hasSize(1);
    }
}