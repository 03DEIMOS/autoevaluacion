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
 
 void crearOpotunidadMejora(String hallazgo, Integer procesoId, Integer caracteristicaId, String eje, String linea_accion, String estado, String responsable);
}
