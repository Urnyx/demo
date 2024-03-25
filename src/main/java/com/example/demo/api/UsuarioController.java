package com.example.demo.api;

import com.example.demo.dto.usuario.UsuarioDto;
import com.example.demo.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController("/api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> getUsuarios(){
        List<UsuarioDto> usuarioDtos = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok().body(usuarioDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable Long id){
        UsuarioDto usuarioDto = usuarioService.buscarUsuarioById(id);
        return ResponseEntity.ok().body(usuarioDto);
    }
}
