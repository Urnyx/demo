package com.example.demo.service.partida;

import com.example.demo.dto.partida.PartidaDto;
import com.example.demo.dto.partida.PartidaToSaveDto;

public interface PartidaService {

    PartidaDto guardarPartida(PartidaToSaveDto partidaToSaveDto);
    PartidaDto buscarPartidaById(Long id);
    PartidaDto buscarPartidaByCreador(String creador);
    PartidaDto actualizarPartida(Long id, PartidaToSaveDto partidaToSaveDto);
    void eliminarPartida(Long id);

}
