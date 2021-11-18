package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Modelo;
import java.util.List;

public interface FactorService {
    List<Factor> getFactores();
    
    List<Factor> getFactoresByModelo(Integer modeloId);
    
    void crearFactor(String codigo, String nombre, Integer modeloId);
    
    void actualizarFactor(Integer factorId, String codigo, String nombre, Integer modeloId);
    
    Factor buscarFactor(Integer id);
    
    List<Factor> buscarPorModeloYConPreguntasAsociadas(Integer modeloId);
    
}
