package com.example.demo.service.mensaje;

import com.example.demo.dto.mensaje.MensajeDto;
import com.example.demo.dto.mensaje.MensajeMapper;
import com.example.demo.dto.mensaje.MensajeToSaveDto;
import com.example.demo.modelo.Mensaje;
import com.example.demo.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensajeServiceImp implements MensajeService{

    MensajeMapper mensajeMapper;

    MensajeRepository mensajeRepository;
    @Autowired
    public MensajeServiceImp(MensajeRepository mensajeRepository, MensajeMapper mensajeMapper){
        this.mensajeMapper = mensajeMapper;
        this.mensajeRepository = mensajeRepository;
    }


    @Override
    public MensajeDto guardarMensaje(MensajeToSaveDto mensajeToSaveDto) {
        Mensaje mensaje = mensajeMapper.mensajeToSaveDtoToMensaje(mensajeToSaveDto);
        Mensaje mensajeSave = mensajeRepository.save(mensaje);
        return mensajeMapper.mensajeToMensajeDto(mensajeSave);
    }

    @Override
    public MensajeDto buscarMensajeById(Long id) {
        Mensaje mensaje = mensajeRepository.findById(id).orElseThrow(MensajeNotFoundException::new);
        return mensajeMapper.mensajeToMensajeDto(mensaje);
    }

    @Override
    public MensajeDto buscarMensajeByCreador(String creador) {
        Mensaje mensaje = mensajeRepository.findByCreador(creador).orElseThrow(MensajeNotFoundException::new);
        return mensajeMapper.mensajeToMensajeDto(mensaje);
    }

    @Override
    public void eliminarMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }
}
