
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.repository.ModeloRepository;
import com.utb.autoevaluacion.service.ModeloService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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

    @Override
    public void crearModelo(String nombre, String descripcion) {
        Modelo modelo = new Modelo();
        
        modelo.setNombre(nombre);
        modelo.setDescripcion(descripcion);
        try {
            modeloRepository.saveAndFlush(modelo);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear el modelo" + e);
            throw e;
        }
    }


    
    
    
}
