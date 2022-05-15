/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.repository.OportunidadMejoraRepository;
import com.utb.autoevaluacion.repository.ProcesoRepository;
import com.utb.autoevaluacion.service.OportunidadMejoraService;
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
public class OportunidadMejoraServiceImpl implements OportunidadMejoraService{
    
    @Autowired
    OportunidadMejoraRepository oportunidadMejoraRepository;  
    
    @Autowired
    ProcesoRepository procesoRepository;  
    
    @Override
    public List<OportunidadMejora> getOportunidadMejoraByProceso(Integer procesoId) {
        return oportunidadMejoraRepository.findOportunidadMejoraByProcesoId(procesoId);
    }

    @Override
    public List<OportunidadMejora> getOportunidadesMejora() {
        return oportunidadMejoraRepository.findAll();
    }

    
}
