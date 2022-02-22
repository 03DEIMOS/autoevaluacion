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

    @Override
    public Integer buscarTotalPersonasContestaronPreguntaItemPreguntaFuente(Integer procesoId, Integer preguntaId, Integer itemPreguntaId, Integer fuenteId) {
        Integer totalPersonasContestaronPregunta = 0;
        try {
            totalPersonasContestaronPregunta = resultadoEvaluacionRepository.findTotalPersonasContestaronPreguntaItemPreguntaFuente(procesoId, preguntaId, itemPreguntaId, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return totalPersonasContestaronPregunta;
    }

    @Override
    public List<ResultadoEvaluacion> buscarPorProcesoItemPreguntaRespuestaFuente(Integer procesoId, Integer itemPreguntaId, Integer respuesta, Integer fuenteId) {
        List<ResultadoEvaluacion> resultadoEvaluaciones = null;
        try {
            resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoItemPreguntaRespuestaFuente(procesoId, itemPreguntaId, respuesta, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }

        return resultadoEvaluaciones;
    }

    @Override
    public List<ResultadoEvaluacion> buscarPorProcesoPreguntaRespuestaFuente(Integer procesoId, Integer preguntaId, Integer respuesta, Integer fuenteId) {
        List<ResultadoEvaluacion> resultadoEvaluaciones = null;
        try {
            resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoPreguntaRespuestaFuente(procesoId, preguntaId, respuesta, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }

        return resultadoEvaluaciones;
    }

    @Override
    public Integer buscarTotalPersonasContestaronPreguntaFuente(Integer procesoId, Integer preguntaId, Integer fuenteId) {
        Integer totalPersonasContestaronPregunta = 0;
        try {
            totalPersonasContestaronPregunta = resultadoEvaluacionRepository.findTotalPersonasContestaronPreguntaFuente(procesoId, preguntaId, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return totalPersonasContestaronPregunta;
    }
    
    @Override
    public List<ResultadoEvaluacion> buscarPorProcesoItemPreguntaFuenteyVariables(Integer procesoId, Integer itemPreguntaId, Integer fuenteId, String variable1, String variable2) {
        List<ResultadoEvaluacion> resultadoEvaluaciones = null;
        try {
            if(variable1!=null && variable1.equals("--")){
                variable1 = null;
            }
            if(variable2!=null && variable2.equals("--")){
                variable2 = null;
            }
            
            if(variable1!=null  && variable2!=null){
                resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoItemPreguntaFuenteyVariables(procesoId, itemPreguntaId, fuenteId, variable1, variable2 );
            }else if(variable1!=null && variable2==null){
                resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoItemPreguntaFuenteyVariable1(procesoId, itemPreguntaId, fuenteId, variable1);
            }else if(variable1==null && variable2!=null){
                resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoItemPreguntaFuenteyVariable2(procesoId, itemPreguntaId, fuenteId, variable2);
            }else if(variable1==null && variable2==null){
                resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoItemPreguntaFuente(procesoId, itemPreguntaId, fuenteId);
            }
            
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado " + e, e);
        }

        return resultadoEvaluaciones;
    }
    
    @Override
    public List<ResultadoEvaluacion> buscarPorProcesoPreguntaFuenteyVariables(Integer procesoId, Integer preguntaId, Integer fuenteId, String variable1, String variable2) {
        log.debug("Ejecutando metodo  buscarPorProcesoPreguntaFuenteyVariables procesoId:{}, preguntaId:{}, fuenteId:{}, variable1:{}, variable2:{} ",
                procesoId, preguntaId, fuenteId, variable1, variable2);
        List<ResultadoEvaluacion> resultadoEvaluaciones = null;
        try {
            if (variable1 != null && variable1.equals("--")) {
                variable1 = null;
            }
            if (variable2 != null && variable2.equals("--")) {
                variable2 = null;
            }

            if (variable1 != null && variable2 != null) {
                resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoPreguntaFuenteyVariables(procesoId, preguntaId, fuenteId, variable1, variable2);
            } else if (variable1 != null && variable2 == null) {
                resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoPreguntaFuenteyVariable1(procesoId, preguntaId, fuenteId, variable1);
            } else if (variable1 == null && variable2 != null) {
                resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoPreguntaFuenteyVariable2(procesoId, preguntaId, fuenteId, variable2);
            } else if (variable1 == null && variable2 == null) {
                resultadoEvaluaciones = resultadoEvaluacionRepository.findByProcesoPreguntaFuente(procesoId, preguntaId, fuenteId);
            }

        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado " + e, e);
        }

        return resultadoEvaluaciones;
    }

}
