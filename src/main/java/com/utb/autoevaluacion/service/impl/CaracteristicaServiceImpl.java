/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.repository.CaracteristicaRepository;
import com.utb.autoevaluacion.repository.FactorRepository;
import com.utb.autoevaluacion.repository.ModeloRepository;
import com.utb.autoevaluacion.service.CaracteristicaService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEIMOS
 */
@Slf4j
@Service
public class CaracteristicaServiceImpl implements CaracteristicaService{
    
    @Autowired
    CaracteristicaRepository caracteristicaRepository;  
    
    @Autowired
    FactorRepository factorRepository; 
    
    @Autowired
    ModeloRepository modeloRepository; 
    
     @Override
    public List<Caracteristica> getCaracteristicas() {
        return caracteristicaRepository.findAll();
    }

    @Override
    public List<Caracteristica> getCaracteristicasByModelo(Integer modeloId) {
        return caracteristicaRepository.findCaracteristicasByModeloId(modeloId);
    }

    @Override
    public void crearCaracteristica(String codigo, String nombre, Integer factorId) {
        
        try {
            Caracteristica caracteristica = new Caracteristica();
            caracteristica.setCodigo(codigo);
            caracteristica.setNombre(nombre);
            Factor factor = factorRepository.findById(factorId).get();
            caracteristica.setFactorId(factor);
            caracteristicaRepository.saveAndFlush(caracteristica);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al crear la caracteristica" + e);
            throw e;
        }
    }
    
    @Override
    public Caracteristica buscarCaracteristica(Integer id) {
        Caracteristica caracteristica = null;
        Optional<Caracteristica> caracteristicaOptional = null;
        try {
            caracteristicaOptional = caracteristicaRepository.findById(id);
            if (caracteristicaOptional.isPresent()) {
                caracteristica = caracteristicaOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado" + e, e);
        }
        return caracteristica;
    }

    @Override
    public void actualizarCaracteristica(Integer caracteristicaId, String codigo, String nombre, Integer factorId) {
         try {
            Caracteristica caracteristica = new Caracteristica();
            caracteristica.setId(caracteristicaId);
            caracteristica.setCodigo(codigo);
            caracteristica.setNombre(nombre);
            Factor factor = factorRepository.findById(factorId).get();
            caracteristica.setFactorId(factor);
            caracteristicaRepository.saveAndFlush(caracteristica);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar la caracteristica" + e);
            throw e;
        }
    }
    
     @Override
    public List<Caracteristica> buscarPorModeloYConPreguntasAsociadas(Modelo modelo){
        List<Caracteristica> caracteristicas = caracteristicaRepository.buscarPorModeloYConPreguntasAsociadas(modeloRepository.findById(modelo.getId()).get());
        return caracteristicas;
    }

    @Override
    public List<Caracteristica> getCaracteristicasByFactor(Integer factorId) {
        List<Caracteristica> caracteristicas = caracteristicaRepository.findCaracteristicasByFactorId(factorId);
        return caracteristicas;
    }
}
