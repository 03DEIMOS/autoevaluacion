/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Facultad;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface FacultadService {
    
    List<Facultad> getFacultades();

    void crearFacultad(String nombre, String descripcion);
    
    void actualizarFacultad(Integer facultadId, String nombre, String descripcion);
    
    Facultad buscarFacultad(Integer facultadId);
}
