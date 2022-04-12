/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.repository.EncuestaRepository;
import com.utb.autoevaluacion.repository.FuenteRepository;
import com.utb.autoevaluacion.service.EncuestaService;
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
public class EncuestaServiceImpl implements EncuestaService {
    
    @Autowired
    EncuestaRepository encuestaRepository;
    
    @Autowired
    FuenteRepository fuenteRepository;
    
    @Override
    public List<Encuesta> getEncuestas() {
        return encuestaRepository.findAll();
    }

    @Override
    public List<Encuesta> getEncuestasByModelo(Integer modeloId) {
        return encuestaRepository.findEncuestasByModeloId(modeloId);
    }

    @Override
    public void crearEncuesta(String codigo, String nombre, String objetivo, String instrucciones, String version, String fecha, Integer fuenteId, List<Pregunta> preguntas) {
        try {
            Encuesta encuesta = new Encuesta();
            encuesta.setCodigo(codigo);
            encuesta.setNombre(nombre);
            encuesta.setObjetivo(objetivo);
            encuesta.setInstrucciones(instrucciones);
            encuesta.setVersion(version);
            encuesta.setFecha(fecha);
            encuesta.setPreguntaList(preguntas);
            Fuente fuente = fuenteRepository.findById(fuenteId).get();
            encuesta.setFuente(fuente);
            encuestaRepository.saveAndFlush(encuesta);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al crear la encuesta " + e);
            throw e;
        }
    }

    @Override
    public void actualizarEncuesta(Integer encuestaId, String codigo, String nombre, String objetivo, String instrucciones, String version, String fecha, Integer fuenteId, List<Pregunta> preguntas) {
        try {
            Encuesta encuesta = new Encuesta();
            encuesta.setId(encuestaId);
            encuesta.setCodigo(codigo);
            encuesta.setNombre(nombre);
            encuesta.setObjetivo(objetivo);
            encuesta.setInstrucciones(instrucciones);
            encuesta.setVersion(version);
            encuesta.setPreguntaList(preguntas);
            encuesta.setFecha(fecha);
            Fuente fuente = fuenteRepository.findById(fuenteId).get();
            encuesta.setFuente(fuente);
            encuestaRepository.saveAndFlush(encuesta);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar la encuesta " + e);
            throw e;
        }
    }

    @Override
    public Encuesta buscarEncuesta(Integer id) {
        Encuesta encuesta = null;
        Optional<Encuesta> encuestaOptional = null;
        try {
            encuestaOptional = encuestaRepository.findById(id);
            if (encuestaOptional.isPresent()) {
                encuesta = encuestaOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado" + e, e);
        }
        return encuesta;
    }
    
    
    @Override
    public Encuesta obtenerEncuestasDePersona(Persona persona) {
        Encuesta encuesta = null;
        try {
            if (persona.getProceso().getEstado().equals("En Ejecución")) {
                encuesta = encuestaRepository.buscarEncuestaPorPersona(persona.getProceso().getModeloId().getId(), persona.getFuente().getId());
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado :{}", e);
        }

        return encuesta;
    }
}
