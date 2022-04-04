package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.model.Pregunta;
import java.util.List;

public interface CaracteristicaService {
    List<Caracteristica> getCaracteristicas();
    
    List<Caracteristica> getCaracteristicasByModelo(Integer modeloId);
    
    List<Caracteristica> getCaracteristicasByFactor(Integer factorId);
    
    void crearCaracteristica(String codigo, String nombre, Integer factorId, List<Pregunta> preguntas);
    
    Caracteristica buscarCaracteristica(Integer caracteristicaId);
    
    void actualizarCaracteristica(Integer caracteristicaId, String codigo, String nombre, Integer factorId, List<Pregunta> preguntas);
    
    List<Caracteristica> buscarPorModeloYConPreguntasAsociadas(Modelo modelo);
}
