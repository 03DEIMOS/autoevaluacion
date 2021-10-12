/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.model.TipoPregunta;
import com.utb.autoevaluacion.repository.PreguntaRepository;
import com.utb.autoevaluacion.service.PreguntaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEIMOS
 */
@Slf4j
@Service
public class PreguntaServiceImpl implements PreguntaService {
    
    @Autowired
    PreguntaRepository preguntaRepository;
    
    @Override
    public List<Pregunta> getPreguntas() {
        return preguntaRepository.findAll();
    }

    @Override
    public void crearPregunta(String codigo, String pregunta, String tipo, Integer preguntaPadre, String repetir) {
        Pregunta objPregunta = new Pregunta();
        
        objPregunta.setCodigo(codigo);
        objPregunta.setPregunta(pregunta);
        //TipoPregunta tipoPregunta = preguntaRepository.findById(tipo);
        //objPregunta.setTipo(tipoPregunta);
        //objPregunta.setPreguntaPadre(preguntaPadre);
        objPregunta.setRepetir(repetir);
        
        try {
            preguntaRepository.saveAndFlush(objPregunta);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear el modelo" + e);
            throw e;
        }
    }

    @Override
    public void actualizarPregunta(Integer preguntaId, String codigo, String pregunta, String tipo, Integer preguntaPadre, String repetir) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pregunta buscarPregunta(Integer preguntaId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
