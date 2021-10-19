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
public class ResultadoEvaluacionServiceImpl implements ResultadoEvaluacionService{
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
    
}
