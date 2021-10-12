package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Caracteristica;
import java.util.List;

public interface CaracteristicaService {
    List<Caracteristica> getCaracteristicas();
    
    List<Caracteristica> getCaracteristicasByModelo(Integer modeloId);
    
    void crearCaracteristica(String codigo, String nombre, Integer factorId);
    
    Caracteristica buscarCaracteristica(Integer caracteristicaId);
    
    void actualizarCaracteristica(Integer caracteristicaId, String codigo, String nombre, Integer factorId);
}
