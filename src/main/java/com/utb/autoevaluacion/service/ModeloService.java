
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Modelo;
import java.util.List;

public interface ModeloService {
    List<Modelo> getModelos();


    Modelo getModeloById(Integer id);
    
    //Para crear Modelo
    Modelo createModelo(Modelo modelo);
}


