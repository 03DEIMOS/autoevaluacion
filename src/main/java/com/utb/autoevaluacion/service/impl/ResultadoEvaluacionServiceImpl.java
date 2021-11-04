/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.ResultadoEvaluacion;
import com.utb.autoevaluacion.repository.PersonaRepository;
import com.utb.autoevaluacion.repository.ResultadoEvaluacionRepository;
import com.utb.autoevaluacion.service.ResultadoEvaluacionService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Slf4j
@Service
public class ResultadoEvaluacionServiceImpl implements ResultadoEvaluacionService {

    @Autowired
    ResultadoEvaluacionRepository resultadoEvaluacionRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public void asignarResultadoEvaluacion(List<ResultadoEvaluacion> resultadoEvaluaciones) {
        Integer personaId = resultadoEvaluaciones.get(0).getPersona().getId();
        log.info("Resultado Evaluacion" + resultadoEvaluaciones.toString());
        try {
            resultadoEvaluaciones.forEach((resultadoEvaluacion) -> {
                try {
                    resultadoEvaluacionRepository.save(resultadoEvaluacion);
                } catch (Exception e) {
                    log.error("Ha ocurrido un error inesperado:{}", e);
                }

            });
            personaRepository.personaTerminaEncuesta(personaId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }
    }

    @Override
    public List<ResultadoEvaluacion> buscarPorProcesoItemPreguntaFuente(Integer procesoId, Integer itemPreguntaId, Integer fuenteId) {
        List<ResultadoEvaluacion> resultadoEvaluaciones = null;
        try {
            resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoItemPreguntaFuente(procesoId, itemPreguntaId, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado " + e, e);
        }

        return resultadoEvaluaciones;
    }

    @Override
    public List<ResultadoEvaluacion> buscarPorProcesoPreguntaFuente(Integer procesoId, Integer preguntaId, Integer fuenteId) {
        List<ResultadoEvaluacion> resultadoEvaluaciones = null;
        try {
            resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoPreguntaFuente(procesoId, preguntaId, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado " + e, e);
        }

        return resultadoEvaluaciones;
    }

    @Override
    public List<ResultadoEvaluacion> buscarPorProcesoItemPreguntaRespuesta(Integer procesoId, Integer itemPreguntaId, Integer respuesta) {
        List<ResultadoEvaluacion> resultadoEvaluaciones = null;
        try {
            resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoItemPreguntaRespuesta(procesoId, itemPreguntaId, respuesta);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }

        return resultadoEvaluaciones;
    }

    @Override
    public List<ResultadoEvaluacion> buscarPorProcesoPreguntaRespuesta(Integer procesoId, Integer preguntaId, Integer respuesta) {
        List<ResultadoEvaluacion> resultadoEvaluaciones = null;
        try {
            resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoPreguntaRespuesta(procesoId, preguntaId, respuesta);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }

        return resultadoEvaluaciones;
    }

    @Override
    public Integer buscarTotalPersonasContestaronPregunta(Integer procesoId, Integer preguntaId) {
        Integer totalPersonasContestaronPregunta = 0;
        try {
            totalPersonasContestaronPregunta = resultadoEvaluacionRepository.findTotalPersonasContestaronPregunta(procesoId, preguntaId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return totalPersonasContestaronPregunta;
    }

    @Override
    public Integer buscarTotalPersonasContestaronPreguntaItemPregunta(Integer procesoId, Integer preguntaId, Integer itemPreguntaId) {
        Integer totalPersonasContestaronPregunta = 0;
        try {
            totalPersonasContestaronPregunta = resultadoEvaluacionRepository.findTotalPersonasContestaronPreguntaItemPregunta(procesoId, preguntaId, itemPreguntaId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return totalPersonasContestaronPregunta;
    }

}
