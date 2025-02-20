package com.unal.una_huella.UNaHuellaLauncher.Services.Interfaces;

import com.unal.una_huella.UNaHuellaLauncher.Entities.Lugar;

import java.util.List;

public interface LugarService {
    List<Lugar> listAllLugares();
    
    Lugar getLugarById(long id);
    
    Lugar saveLugar(Lugar lugar);
    
    void deleteLugar(long id);
}
