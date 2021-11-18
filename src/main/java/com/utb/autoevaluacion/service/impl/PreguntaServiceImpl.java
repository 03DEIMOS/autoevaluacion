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
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    PreguntaRepository preguntaRepository;

    @Override
    public List<Pregunta> getPreguntas() {
        return preguntaRepository.findAll();
    }

    @Override
    public Pregunta crearPregunta(Pregunta pregunta) {
        try {
            preguntaRepository.saveAndFlush(pregunta);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear la pregunta" + e);
            throw e;
        }
        return pregunta;
    }

    @Override
    public Pregunta buscarPregunta(Integer id) {
        Pregunta pregunta = null;
        Optional<Pregunta> preguntaOptional = null;
        try {
            preguntaOptional = preguntaRepository.findById(id);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e, e);
        }
        if (preguntaOptional.isPresent()) {
            pregunta = preguntaOptional.get();
        }
        return pregunta;
    }

    @Override
    public Pregunta actualizarPregunta(Pregunta pregunta) {
        try {
            preguntaRepository.saveAndFlush(pregunta);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear la pregunta" + e);
            throw e;
        }
        return pregunta;
    }

    @Override
    public List<Pregunta> getPreguntasPorProceso(Integer procesoId) {
        return preguntaRepository.buscarPreguntasPorProceso(procesoId);
    }

    @Override
    public List<Pregunta> getPreguntasPorProcesoyPublico(Integer procesoId, Integer publicoId) {
        return preguntaRepository.buscarPreguntasPorProcesoyPublico(procesoId, publicoId);
    }

}
