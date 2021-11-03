/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Persona;
import java.util.List;

public interface PersonaService {
    
    List<Persona> buscarPersonaPorUsuarioActivaYEsMuestra(String usuario);
    
    Persona buscarPersona(Integer personaId);
    
    List<Persona> buscarPoblacionPorProcesoYFuenteActivaYEsMuestra(Integer procesoId, Integer fuenteId);
    
    List<Persona> muestraPorProceso(Integer procesoId);
    
    List<Persona> personasEncuestaTerminadaPorProceso(Integer procesoId);
    
    Integer cantidadMuestraEncuestaTerminadaPorProcesoFuente(Integer procesoId, Integer fuenteId);
    
    Integer cantidadTotalMuestraPorProcesoFuente(Integer procesoId, Integer fuenteId);
}
