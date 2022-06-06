/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Seguimiento;
import java.util.List;

/**
 *
 * @author DEIMOS
 */
public interface SeguimientoService {
    List<Seguimiento> getSeguimientoByOportunidadMejora(Integer idHallazgo);
    
    void crearSeguimiento(String fechaProgramada, String fechaRealizado, Integer porcentajeAvance, String avances, Integer idHallazgo);

}
