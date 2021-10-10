package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.repository.FactorRepository;
import com.utb.autoevaluacion.repository.ModeloRepository;
import com.utb.autoevaluacion.service.FactorService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FactorServiceImpl implements FactorService {

    @Autowired
    FactorRepository factorRepository;
    
    @Autowired
    ModeloRepository modeloRepository;
    
    
    @Override
    public List<Factor> getFactores() {
        return factorRepository.findAll();
    }

    @Override
    public List<Factor> getFactoresByModelo(Integer m) {
        return factorRepository.findFactorByModeloId(m);
    }

    @Override
    public void crearFactor(String codigo, String nombre, Integer modeloId) {
        Factor factor = new Factor();
        factor.setCodigo(codigo);
        factor.setNombre(nombre);
        try {
            Modelo modelo = modeloRepository.findById(modeloId).get();
            factor.setModeloId(modelo);
            factorRepository.saveAndFlush(factor);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear el factor" + e);
            throw e;
        }
    }

}
