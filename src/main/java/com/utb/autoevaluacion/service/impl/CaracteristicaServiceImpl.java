/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.repository.CaracteristicaRepository;
import com.utb.autoevaluacion.service.CaracteristicaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEIMOS
 */
@Service
public class CaracteristicaServiceImpl implements CaracteristicaService{
    
    @Autowired
    CaracteristicaRepository caracteristicaRepository;   
    
     @Override
    public List<Caracteristica> getCaracteristicas() {
        return caracteristicaRepository.findAll();
    }
    
}
