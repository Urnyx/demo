package com.example.demo.service.usuario;

import com.example.demo.dto.usuario.UsuarioDto;
import com.example.demo.dto.usuario.UsuarioMapper;
import com.example.demo.dto.usuario.UsuarioToSaveDto;
import com.example.demo.modelo.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.NotAbleToDeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioServiceImp implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;
    @Autowired
    public UsuarioServiceImp (UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper){
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }
    @Override
    public UsuarioDto guardarUsuario(UsuarioToSaveDto usuarioToSaveDto) {
        Usuario usuario = usuarioMapper.usuarioToSaveDtoToUsuario(usuarioToSaveDto);
        Usuario usuarioSave = usuarioRepository.save(usuario);
        return usuarioMapper.usuarioToUsuarioDto(usuarioSave);
    }
    @Override
    public UsuarioDto buscarUsuarioById(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
        return usuarioMapper.usuarioToUsuarioDto(usuario);
    }

    @Override
    public UsuarioDto buscarUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(UsuarioNotFoundException::new);
        return usuarioMapper.usuarioToUsuarioDto(usuario);
    }

    @Override
    public UsuarioDto actualizarUsuario(Long id,UsuarioToSaveDto usuarioToSaveDto) {
        return usuarioRepository.findById(id).map(usuarioIntDb ->{
            usuarioIntDb.setNombre(usuarioToSaveDto.nombre());
            usuarioIntDb.setUserName(usuarioToSaveDto.userName());
            usuarioIntDb.setPassword(usuarioToSaveDto.password());
            usuarioIntDb.setEmail(usuarioToSaveDto.email());

            Usuario usuario = usuarioRepository.save(usuarioIntDb);
            return usuarioMapper.usuarioToUsuarioDto(usuario);
        }).orElseThrow(()-> new UsuarioNotFoundException("Usuario no encontrado"));
    }

    @Override
    public List<UsuarioDto> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> usuarioMapper.usuarioToUsuarioDto(usuario)).toList();
    }

    @Override
    public void deleteUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new NotAbleToDeleteException("Usuario no encontrado"));
        usuarioRepository.deleteById(id);
    }
}
