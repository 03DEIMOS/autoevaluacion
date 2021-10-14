/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Proceso;
import java.util.List;


public interface ProcesoService {
    
    Proceso buscarProceso(Integer id);
    
    void crearProceso(Proceso proceso);
    
    void eliminarProceso(Integer id);
    
    void actualizarProceso(Proceso proceso);
    
    List<Proceso> buscarProcesos();
    
    void cambiarEstadoProceso(Proceso proceso, String estado);
}
