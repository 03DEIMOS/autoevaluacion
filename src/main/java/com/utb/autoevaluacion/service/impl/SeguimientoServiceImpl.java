package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.service.SeguimientoService;
import com.utb.autoevaluacion.repository.SeguimientoRepository;
import com.utb.autoevaluacion.model.Seguimiento;
import com.utb.autoevaluacion.repository.OportunidadMejoraRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DEIMOS
 */
@Slf4j
@Service
public class SeguimientoServiceImpl implements SeguimientoService{
    
    @Autowired
    SeguimientoRepository seguimientoRepository;
    
    @Autowired
    OportunidadMejoraRepository oportunidadMejoraRepository;
    
    @Override
    public List<Seguimiento> getSeguimientoByOportunidadMejora(Integer idHallazgo) {
        return seguimientoRepository.findSeguimientoByOportunidadMejoraId(idHallazgo);
    }

    @Override
    public void crearSeguimiento(String fechaProgramada, String fechaRealizado, Integer porcentajeAvance, String avances, Integer idHallazgo) {
        Seguimiento seguimiento = new Seguimiento();

        OportunidadMejora oportunidadMejora = oportunidadMejoraRepository.findById(idHallazgo).get();
        seguimiento.setOportunidadMejora(oportunidadMejora);
        seguimiento.setFechaProgramada(fechaProgramada);
        seguimiento.setFechaRealizado(fechaRealizado);
        seguimiento.setPorcentajeAvance(porcentajeAvance);
        seguimiento.setAvances(avances);
        try {
            seguimientoRepository.saveAndFlush(seguimiento);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear la Actividad de Seguimiento: ", e);
            throw e;
        }
    }

    @Override
    public Seguimiento buscarSeguimiento(Integer idSeguimiento) {
        Seguimiento seguimiento = null;
        Optional<Seguimiento> seguimientoOptional = null;
        try {
            seguimientoOptional = seguimientoRepository.findById(idSeguimiento);
            if (seguimientoOptional.isPresent()) {
                seguimiento = seguimientoOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado", e);
        }
        return seguimiento;
        
    }
    
}
