/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Programa;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ProgramaService {
    
    List<Programa> getProgramas();

    void crearPrograma(String nombre, String descripcion, String modalidad, String tipoformacion, Integer facultadId);
    
    void actualizarPrograma(Integer programaId, String nombre, String descripcion, String modalidad, String tipoformacion, Integer facultadId);
    
    Programa buscarPrograma(Integer programaId);
}
