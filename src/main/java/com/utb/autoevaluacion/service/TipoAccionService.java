/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.TipoAccion;
import com.utb.autoevaluacion.model.Variable;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface TipoAccionService {
    
    List<TipoAccion> getTiposAccion();

    TipoAccion getTipoAccionById(Integer id);
    
    TipoAccion getTipoAccionByTipo(String tipo);
    
    void crearTipoAccion(TipoAccion tipoAccion);
    
    void actualizarTipoAccion(TipoAccion tipoAccion);    
    
}
