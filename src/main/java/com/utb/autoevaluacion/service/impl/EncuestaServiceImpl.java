/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.repository.EncuestaRepository;
import com.utb.autoevaluacion.service.EncuestaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEIMOS
 */
@Service
public class EncuestaServiceImpl implements EncuestaService {
    
    @Autowired
    EncuestaRepository encuestaRepository;
    
    @Override
    public List<Encuesta> getEncuestas() {
        return encuestaRepository.findAll();
    }
    
}
