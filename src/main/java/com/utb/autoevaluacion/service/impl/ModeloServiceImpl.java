
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.service.ModeloService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ModeloServiceImpl implements ModeloService{

    @Override
    public List<Modelo> getModelos() {
        Modelo m = new Modelo();
        m.setId(1);
        m.setNombre("Óscar");
        m.setDescripcion("Óscar");
        List<Modelo> mm = new ArrayList<>();
        mm.add(m);
        return mm;
               
    }
    
}
