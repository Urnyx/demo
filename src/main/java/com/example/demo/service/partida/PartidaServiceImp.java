package com.example.demo.service.partida;

import com.example.demo.dto.partida.PartidaDto;
import com.example.demo.dto.partida.PartidaMapper;
import com.example.demo.dto.partida.PartidaToSaveDto;
import com.example.demo.modelo.Partida;
import com.example.demo.repository.PartidaRepository;
import com.example.demo.service.NotAbleToDeleteException;

public class PartidaServiceImp implements PartidaService{

    PartidaMapper partidaMapper;

    PartidaRepository partidaRepository;

    public PartidaServiceImp(PartidaMapper partidaMapper, PartidaRepository partidaRepository){
        this.partidaRepository = partidaRepository;
        this.partidaMapper = partidaMapper;
    }
    @Override
    public PartidaDto guardarPartida(PartidaToSaveDto partidaToSaveDto) {
        Partida partida = partidaMapper.partidaToSaveDtoToPartida(partidaToSaveDto);
        Partida partidaSave = partidaRepository.save(partida);
        return partidaMapper.partidaToPartidaDto(partidaSave);
    }

    @Override
    public PartidaDto buscarPartidaById(Long id) {
        Partida partida = partidaRepository.findById(id).orElseThrow(PartidaNotFoundException::new);
        return partidaMapper.partidaToPartidaDto(partida);
    }

    @Override
    public PartidaDto buscarPartidaByCreador(String creador) {
        Partida partida = partidaRepository.findByCreador(creador).orElseThrow(PartidaNotFoundException::new);
        return partidaMapper.partidaToPartidaDto(partida);
    }

    @Override
    public PartidaDto actualizarPartida(Long id, PartidaToSaveDto partidaToSaveDto) {
        return partidaRepository.findById(id)
                .map(partidaInDb ->{
                    partidaInDb.setCity(partidaToSaveDto.city());
                    partidaInDb.setDate(partidaToSaveDto.date());
                    partidaInDb.setComentarios(partidaToSaveDto.comentarios());
                    partidaInDb.setDeporte(partidaToSaveDto.deporte());
                    partidaInDb.setHoraInicio(partidaToSaveDto.horaInicio());
                    partidaInDb.setHoraFinal(partidaToSaveDto.horaFinal());
                    partidaInDb.setParticipantes(partidaToSaveDto.participantes());
                    partidaInDb.setProvinces(partidaToSaveDto.provinces());
                    partidaInDb.setSuplentes(partidaToSaveDto.suplentes());

                    Partida partida = partidaRepository.save(partidaInDb);

                    return partidaMapper.partidaToPartidaDto(partida);
                }).orElseThrow(()-> new PartidaNotFoundException("partida no encontrada"));
    }

    @Override
    public void eliminarPartida(Long id) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(()-> new NotAbleToDeleteException("partida no encontrada"));
        partidaRepository.deleteById(id);
    }
}
