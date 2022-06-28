/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.PlanMejoramiento;
import com.utb.autoevaluacion.repository.PlanMejoramientoRepository;
import com.utb.autoevaluacion.service.PlanMejoramientoService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
@Slf4j
public class PlanMejoramientoServiceImpl implements PlanMejoramientoService{
    
    @Autowired
    PlanMejoramientoRepository planMejoramientoRepository;
    
    @Override
    public PlanMejoramiento buscarPlanMejoramiento(Integer id) {
        PlanMejoramiento planMejoramiento = null;
        Optional<PlanMejoramiento> planMejoramientoOptional = null;
        try {
            planMejoramientoOptional = planMejoramientoRepository.findById(id);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} " , e);
        }
        if (planMejoramientoOptional.isPresent()) {
            planMejoramiento = planMejoramientoOptional.get();
        }
        return planMejoramiento;
    }

    @Override
    public void crearPlanMejoramiento(PlanMejoramiento planMejoramiento) {
        try {
            planMejoramientoRepository.saveAndFlush(planMejoramiento);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }
    }

    @Override
    public void eliminarPlanMejoramiento(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarPlanMejoramiento(PlanMejoramiento planMejoramiento) {
        try {
            planMejoramientoRepository.saveAndFlush(planMejoramiento);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} " , e);
        }
    }

    @Override
    public List<PlanMejoramiento> buscarPlanesMejoramiento() {
        List<PlanMejoramiento> planesMejoramiento = null;
        try {
            planesMejoramiento = planMejoramientoRepository.findAll();
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }
        return planesMejoramiento;
    }

    @Override
    public void cambiarEstadoPlanMejoramiento(PlanMejoramiento planMejoramiento, String estado) {
        planMejoramiento.setEstado(estado);
        planMejoramiento.setFechaCierre("" + LocalDate.now());
        try {
            planMejoramientoRepository.saveAndFlush(planMejoramiento);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }
    }
    
}
