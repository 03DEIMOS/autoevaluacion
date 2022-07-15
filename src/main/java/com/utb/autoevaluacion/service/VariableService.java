/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Variable;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface VariableService {
    
    List<Variable> getVariables();

    Variable getVariableById(Integer id);
    
    Variable getVariableByLlave(String llave);
    
    void crearVariable(String nombre, String descripcion);
    
    void actualizarVariable(Integer variableId, String llave, String valor);    
    
}
