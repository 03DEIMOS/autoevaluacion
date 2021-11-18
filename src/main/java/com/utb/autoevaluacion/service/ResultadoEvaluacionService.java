/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.ResultadoEvaluacion;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ResultadoEvaluacionService {
    
    void asignarResultadoEvaluacion(List<ResultadoEvaluacion> resultadoEvaluaciones);
    
    List<ResultadoEvaluacion> buscarPorProcesoItemPreguntaFuente(Integer procesoId, Integer itemPreguntaId, Integer fuenteId);
    
    List<ResultadoEvaluacion> buscarPorProcesoPreguntaFuente(Integer procesoId, Integer preguntaId, Integer fuenteId);
    
    List<ResultadoEvaluacion> buscarPorProcesoItemPreguntaRespuesta(Integer procesoId, Integer itemPreguntaId, Integer respuesta);
    
    List<ResultadoEvaluacion> buscarPorProcesoItemPreguntaRespuestaFuente(Integer procesoId, Integer itemPreguntaId, Integer respuesta, Integer fuenteId);
    
    List<ResultadoEvaluacion> buscarPorProcesoPreguntaRespuesta(Integer procesoId, Integer preguntaId, Integer respuesta);
    
    Integer buscarTotalPersonasContestaronPregunta(Integer procesoId, Integer preguntaId);
    
    Integer buscarTotalPersonasContestaronPreguntaItemPregunta(Integer procesoId, Integer preguntaId, Integer itemPreguntaId);
    
    Integer buscarTotalPersonasContestaronPreguntaItemPreguntaFuente(Integer procesoId, Integer preguntaId, Integer itemPreguntaId, Integer fuenteId);
    
    List<ResultadoEvaluacion> buscarPorProcesoPreguntaRespuestaFuente(Integer procesoId, Integer preguntaId, Integer respuesta, Integer fuenteId);
    
    Integer buscarTotalPersonasContestaronPreguntaFuente(Integer procesoId, Integer preguntaId, Integer fuenteId);
}
