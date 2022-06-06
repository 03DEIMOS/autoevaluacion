/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.OportunidadMejora;
import java.util.List;

/**
 *
 * @author DEIMOS
 */
public interface OportunidadMejoraService {
    
 List<OportunidadMejora> getOportunidadesMejora();  
 
 List<OportunidadMejora> getOportunidadMejoraByProceso(Integer procesoId);
 
 void crearOportunidadMejora(String hallazgo, Integer procesoId, Integer caracteristicaId, String eje, String lineaAccion, 
         String estado, String responsable, String fechaInicio, String fechaFinal);
 
 void actualizarOportunidadMejora(Integer hallazgoId, String hallazgo, Integer procesoId, Integer caracteristicaId, String eje, String lineaAccion, 
         String estado, String responsable, String fechaInicio, String fechaFinal);
 
 OportunidadMejora buscarOportunidadMejora(Integer id);
 
}
