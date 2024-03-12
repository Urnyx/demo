package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Mensaje;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer>{


    @Transactional
    @Modifying
    @Query("UPDATE Mensaje m SET m.contenido = :contenido WHERE m.id = :mensajeId")
    public void updateContenidoById(Long mensajeId, String contenido);
}
