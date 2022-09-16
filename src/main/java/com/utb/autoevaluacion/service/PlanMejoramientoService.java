/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.PlanMejoramiento;
import java.util.List;

/**
 *
 * @author ASUS
 */

public interface PlanMejoramientoService {
    
    PlanMejoramiento buscarPlanMejoramiento(Integer id);
    
    void crearPlanMejoramiento(PlanMejoramiento planMejoramiento);
    
    void eliminarPlanMejoramiento(Integer id);
    
    void actualizarPlanMejoramiento(PlanMejoramiento planMejoramiento);
    
    List<PlanMejoramiento> buscarPlanesMejoramiento();
    
    List<PlanMejoramiento> buscarPlanesMejoramientoByUserId(Integer userId); 
    
    List<PlanMejoramiento> buscarPlanesMejoramientoInstitucionales();
    
    void cambiarEstadoPlanMejoramiento(PlanMejoramiento planMejoramiento, String estado);
}
