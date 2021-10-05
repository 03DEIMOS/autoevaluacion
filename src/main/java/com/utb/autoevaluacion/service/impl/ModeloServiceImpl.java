
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.repository.ModeloRepository;
import com.utb.autoevaluacion.service.ModeloService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeloServiceImpl implements ModeloService{
    
    @Autowired
    ModeloRepository modeloRepository;       
    
    @Override
    public List<Modelo> getModelos() {
        return modeloRepository.findAll();
    }

    @Override
    public Modelo getModeloById(Integer id) {
        return modeloRepository.getOne(id);
    }
    
    
    
}
