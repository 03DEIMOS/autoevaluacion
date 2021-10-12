/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Pregunta;
import java.util.List;

/**
 *
 * @author DEIMOS
 */
public interface PreguntaService {
    List<Pregunta> getPreguntas();
    
    void crearPregunta(String codigo, String pregunta, String tipo, Integer preguntaPadre, String repetir);
    
    void actualizarPregunta(Integer preguntaId, String codigo, String pregunta, String tipo, Integer preguntaPadre, String repetir);
    
    Pregunta buscarPregunta(Integer preguntaId);
    
}
