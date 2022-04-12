/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Fuente;
import java.util.List;

/**
 *
 * @author Win 10
 */
public interface FuenteService {
    
    Fuente buscarFuente(Integer id);
    
    List<Fuente> buscarFuentes();
    
    List<Fuente> buscarFuentesXproceso(Integer procesoId);
    
    List<Fuente> buscarFuentesXmodeloXpregunta(Integer idModelo, Integer idPregunta) ;
    
    void crearFuente(String nombre, String descripcion);
    
    void actualizarFuente(Integer fuenteId, String nombre, String descripcion);
    
}
