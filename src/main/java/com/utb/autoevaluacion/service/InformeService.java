/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.dto.InformeCaracteristicasDTO;
import com.utb.autoevaluacion.dto.InformeDetalleCaracteristicaDTO;
import com.utb.autoevaluacion.dto.InformeFactoresDTO;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InformeService {
    
    List<Object> estadoGeneralDelProceso(Integer procesoId);
    
    List<Object> informeDMAPorProceso(Integer procesoId);
    
    InformeCaracteristicasDTO informeCaracteristicasPorProceso(Integer procesoId);
    
    InformeFactoresDTO informeFactoresPorProceso(Integer procesoId);
    
    List<Object> informePreguntasPorProceso(Integer procesoId);
    
    List<Object> informePreguntasPorProcesoyPublico(Integer procesoId, Integer publico);
    
    InformeDetalleCaracteristicaDTO informeDetallePorCaracteristica(Integer procesoId, Integer caracteristicaId);
    
    InformeCaracteristicasDTO informeDetallePorFactor(Integer procesoId, Integer factorId);

}
