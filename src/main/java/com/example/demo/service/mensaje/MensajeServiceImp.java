package com.example.demo.service.mensaje;

import com.example.demo.dto.mensaje.MensajeDto;
import com.example.demo.dto.mensaje.MensajeMapper;
import com.example.demo.dto.mensaje.MensajeToSaveDto;
import com.example.demo.modelo.Mensaje;
import com.example.demo.repository.MensajeRepository;
import com.example.demo.service.NotAbleToDeleteException;
import com.example.demo.service.partida.PartidaNotFoundException;
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
        MensajeDto mensajeDto = mensajeMapper.mensajeToMensajeDto(mensajeSave);
        return mensajeDto;
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
    public MensajeDto actualizarMensaje(Long id, MensajeToSaveDto mensajeToSaveDto) {
        return mensajeRepository.findById(id)
                .map(mensajeInDb ->{
                    mensajeInDb.setCreador(mensajeToSaveDto.creador());
                    mensajeInDb.setContenido(mensajeToSaveDto.destinatario());
                    mensajeInDb.setCreate_at(mensajeToSaveDto.create_at());
                    mensajeInDb.setDestinatario(mensajeToSaveDto.destinatario());

                    Mensaje mensaje = mensajeRepository.save(mensajeInDb);

                    return mensajeMapper.mensajeToMensajeDto(mensaje);
                }).orElseThrow(()-> new MensajeNotFoundException("Mensaje no encontrado"));
    }

    @Override
    public void eliminarMensaje(Long id) {
        mensajeRepository.findById(id).orElseThrow(()-> new NotAbleToDeleteException("Mensaje no encontrado"));
        mensajeRepository.deleteById(id);
    }
}
