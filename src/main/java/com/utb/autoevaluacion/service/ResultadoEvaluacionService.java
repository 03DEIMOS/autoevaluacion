/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.ResultadoEvaluacion;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ResultadoEvaluacionService {
    
    void asignarResultadoEvaluacion(List<ResultadoEvaluacion> resultadoEvaluaciones);
}
