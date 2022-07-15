/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.model.TipoAccion;
import java.util.List;

/**
 *
 * @author DEIMOS
 */
public interface OportunidadMejoraService {
    
 List<OportunidadMejora> getOportunidadesMejora();  
 
 List<OportunidadMejora> getOportunidadMejoraByPlanMejoramiento(Integer planMejoramientoId);
 
 List<OportunidadMejora> getOportunidadMejoraByPlanMejoramientoAndFactor(Integer planMejoramientoId, Integer factorId);
 
 List<OportunidadMejora> getOportunidadMejoraByPlanMejoramientoAndStatus(Integer planMejoramientoId, TipoAccion status);
 
 void crearOportunidadMejora(String hallazgo, Integer planMejoramientoId, Integer caracteristicaId, String eje, String lineaAccion, 
         Integer estadoId, String tipo, String responsable, String fechaInicio, String fechaFinal
         ,String recurso, String indicador, String meta, String lineaBase);
 
 void actualizarOportunidadMejora(Integer hallazgoId, String hallazgo, Integer planMejoramientoId, Integer caracteristicaId, String eje, String lineaAccion, 
         Integer estadoId, String tipo, String responsable, String fechaInicio, String fechaFinal
        ,String recurso, String indicador, String meta, String lineaBase);
 
 OportunidadMejora buscarOportunidadMejora(Integer id);
 
 void eliminarOportunidadMejora(Integer oportunidadMejoraId);
 
}
