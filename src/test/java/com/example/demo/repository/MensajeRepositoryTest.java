package com.example.demo.repository;

import com.example.demo.modelo.Mensaje;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MensajeRepositoryTest extends AbstractDBTest{
    MensajeRepository mensajeRepository;

    @Autowired
    public MensajeRepositoryTest(MensajeRepository mensajeRepository){
        this.mensajeRepository = mensajeRepository;
    }


    void mensajes(){
        Mensaje mensaje1 = Mensaje.builder()
                .id(1L)
                .creador("Fulanito de tal")
                .destinatario("tal de fulanito")
                .contenido("waos")
                .build();

        Mensaje mensaje2 = Mensaje.builder()
                .id(2L)
                .creador("Fulanito de tal007")
                .destinatario("tal de fulanito007")
                .contenido("waos007").build();

        mensajeRepository.save(mensaje1);
        mensajeRepository.save(mensaje2);

    }
    @Test
    void  buscarTodosLosMensajes(){
        mensajes();
        List<Mensaje> mensajes = mensajeRepository.findAll();
        mensajeRepository.flush();
        assertThat(mensajes).hasSize(2);
    }
    @Test
    void guardarMensajes(){
        Mensaje mensaje1 = Mensaje.builder()
                .id(1L)
                .creador("Fulanito de tal")
                .destinatario("tal de fulanito")
                .contenido("waos")
                .build();
        Mensaje mensaje = mensajeRepository.save(mensaje1);
        mensajeRepository.flush();
        assertThat(mensaje).isNotNull();
        assertThat(mensaje.getId()).isNotNull();
    }
    @Test
    void buscarMensajesPorId(){
        mensajes();
        Optional<Mensaje> mensaje = mensajeRepository.findById(1);
        mensajeRepository.flush();
        assertThat(mensaje).isNotNull();
        assertThat(mensaje.get().getId()).isNotNull();
    }
    @Test
    void borrarPorId(){
        mensajes();
        List<Mensaje> mensajesAntes = mensajeRepository.findAll();
        mensajeRepository.deleteById(1);
        List<Mensaje> mensajesDespues = mensajeRepository.findAll();
        mensajeRepository.flush();
        assertThat(mensajesDespues.size()).isEqualTo(mensajesAntes.size()-1);
    }

    void updateMensajeSpy(){
        Mensaje mensaje1 = Mensaje.builder()
                .id(1L)
                .creador("Fulanito de tal")
                .destinatario("tal de fulanito")
                .contenido("waos")
                .build();

    }
}