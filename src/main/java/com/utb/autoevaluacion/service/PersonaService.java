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
    
    List<String> buscarVariablesPorProcesoYFuenteActivaYTerminado(Integer procesoId, Integer fuenteId, String variable);
    
    List<Persona> muestraPorProceso(Integer procesoId);
    
    List<Persona> personasEncuestaTerminadaPorProceso(Integer procesoId);
    
    Integer cantidadMuestraEncuestaTerminadaPorProcesoFuente(Integer procesoId, Integer fuenteId);
    
    Integer cantidadTotalMuestraPorProcesoFuente(Integer procesoId, Integer fuenteId);
    
    void crearEvaluador(String identificacion, String codigo, String nombre, String apellido, String correo, String variable1, String variable2, Integer fuenteId, Integer procesoId);
    
    void actualizarEvaluador(Integer personaId, String identificacion, String codigo, String nombre, String apellido, String correo, String variable1, String variable2, Integer fuenteId, Integer procesoId);
    
}
