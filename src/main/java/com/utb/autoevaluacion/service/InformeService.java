/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InformeService {
    
    List<Object> estadoGeneralDelProceso(Integer procesoId);
    
    List<Object> informeDMAPorProceso(Integer procesoId);
    
    //List<Object> informePreguntasPorProceso(Integer procesoId);

}
