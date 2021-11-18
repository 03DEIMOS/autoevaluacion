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
    
    List<Pregunta> getPreguntasPorProceso(Integer procesoId);

    Pregunta crearPregunta(Pregunta pregunta);

    Pregunta actualizarPregunta(Pregunta pregunta);

    Pregunta buscarPregunta(Integer preguntaId);
    
    List<Pregunta> getPreguntasPorProcesoyPublico(Integer procesoId, Integer publico);

}
