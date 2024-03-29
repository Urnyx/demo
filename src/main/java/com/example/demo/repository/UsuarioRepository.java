package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    Optional<Usuario> findByUsername(String username);
    
}
