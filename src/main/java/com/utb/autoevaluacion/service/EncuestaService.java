/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Pregunta;
import java.util.List;

/**
 *
 * @author DEIMOS
 */
public interface EncuestaService {
    List<Encuesta> getEncuestas();
    
    List<Encuesta> getEncuestasByModelo(Integer modeloId);
    
    void crearEncuesta(String codigo, String nombre, String objetivo, String instrucciones, String version, String fecha, Integer fuenteId, List<Pregunta> preguntas);
    
    void actualizarEncuesta(Integer encuestaId, String codigo, String nombre, String objetivo, String instrucciones, String version, String fecha, Integer fuenteId, List<Pregunta> preguntas);
    
    Encuesta buscarEncuesta(Integer id);
    
    Encuesta obtenerEncuestasDePersona(Persona persona);
    
}
