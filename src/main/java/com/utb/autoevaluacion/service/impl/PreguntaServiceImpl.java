/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.repository.PreguntaRepository;
import com.utb.autoevaluacion.service.PreguntaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEIMOS
 */
@Service
public class PreguntaServiceImpl implements PreguntaService {
    
    @Autowired
    PreguntaRepository preguntaRepository;
    
    @Override
    public List<Pregunta> getPreguntas() {
        return preguntaRepository.findAll();
    }
    
}
