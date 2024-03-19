package com.example.demo.service.partida;

import com.example.demo.dto.partida.PartidaDto;
import com.example.demo.dto.partida.PartidaMapper;
import com.example.demo.dto.partida.PartidaToSaveDto;
import com.example.demo.modelo.Partida;
import com.example.demo.repository.PartidaRepository;

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
}
