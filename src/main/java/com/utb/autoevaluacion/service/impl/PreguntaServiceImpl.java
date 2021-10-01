/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.service.PreguntaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEIMOS
 */
@Service
public class PreguntaServiceImpl implements PreguntaService {
    @Override
    public List<Pregunta> getPregunta() {
        Pregunta p = new Pregunta();
        p.setId(1);
        p.setCodigo("P01");
        p.setPregunta("Pregunta 1");
        List<Pregunta> listPregunta = new ArrayList<>();
        
        listPregunta.add(p);
        return listPregunta;
    }
}
