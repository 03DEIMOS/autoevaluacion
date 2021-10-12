/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Encuesta;
import java.util.List;

/**
 *
 * @author DEIMOS
 */
public interface EncuestaService {
    List<Encuesta> getEncuestas();
    
    List<Encuesta> getEncuestasByModelo(Integer modeloId);
    
    void crearEncuesta(String codigo, String nombre, String objetivo, String instrucciones, String version, String fecha);
    
    void actualizarEncuesta(Integer encuestaId, String codigo, String nombre, String objetivo, String instrucciones, String version, String fecha);
    
    Encuesta buscarEncuesta(Integer id);
    
}
