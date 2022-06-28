/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.model.PlanMejoramiento;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.repository.OportunidadMejoraRepository;
import com.utb.autoevaluacion.repository.ProcesoRepository;
import com.utb.autoevaluacion.repository.CaracteristicaRepository;
import com.utb.autoevaluacion.repository.PlanMejoramientoRepository;
import com.utb.autoevaluacion.service.OportunidadMejoraService;
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
public class OportunidadMejoraServiceImpl implements OportunidadMejoraService {

    @Autowired
    OportunidadMejoraRepository oportunidadMejoraRepository;

    @Autowired
    PlanMejoramientoRepository planMejoramientoRepository;

    @Autowired
    CaracteristicaRepository caracteristicaRepository;

    @Override
    public List<OportunidadMejora> getOportunidadMejoraByPlanMejoramiento(Integer planMejoramientoId) {
        return oportunidadMejoraRepository.findOportunidadMejoraByPlanMejoramientoId(planMejoramientoId);
    }
    
    @Override
    public List<OportunidadMejora> getOportunidadMejoraByPlanMejoramientoAndStatus(Integer planMejoramientoId, String status) {
        return oportunidadMejoraRepository.findOportunidadMejoraByPlanMejoramientoIdAndStatus(planMejoramientoId, status);
    }

    @Override
    public List<OportunidadMejora> getOportunidadesMejora() {
        return oportunidadMejoraRepository.findAll();
    }

    @Override
    public void crearOportunidadMejora(String hallazgo, Integer planMejoramientoId, Integer caracteristicaId, String eje, String lineaAccion,
            String estado, String tipo, String responsable, String fechaInicio, String fechaFinal) {
        OportunidadMejora oportunidadMejora = new OportunidadMejora();

        PlanMejoramiento planMejoramiento = planMejoramientoRepository.findById(planMejoramientoId).get();
        oportunidadMejora.setHallazgo(hallazgo);
        oportunidadMejora.setPlanMejoramientoId(planMejoramiento);
        Caracteristica caracteristica = caracteristicaRepository.findById(caracteristicaId).get();
        oportunidadMejora.setCaracteristicaId(caracteristica);
        oportunidadMejora.setEje(eje);
        oportunidadMejora.setLineaAccion(lineaAccion);
        oportunidadMejora.setEstado(estado);
        oportunidadMejora.setTipo(tipo);
        oportunidadMejora.setResponsable(responsable);
        oportunidadMejora.setFechaInicio(fechaInicio);
        oportunidadMejora.setFechaFin(fechaFinal);
        try {
            oportunidadMejoraRepository.saveAndFlush(oportunidadMejora);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear la oportunidad de mejoramiento:{} ", e);
            throw e;
        }
    }

    @Override
    public OportunidadMejora buscarOportunidadMejora(Integer id) {
        OportunidadMejora oportunidadMejora = null;
        Optional<OportunidadMejora> oportunidadMejoraOptional = null;
        try {
            oportunidadMejoraOptional = oportunidadMejoraRepository.findById(id);
            if (oportunidadMejoraOptional.isPresent()) {
                oportunidadMejora = oportunidadMejoraOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return oportunidadMejora;
    }

    @Override
    public void actualizarOportunidadMejora(Integer hallazgoId, String hallazgo, Integer planMejoramientoId, Integer caracteristicaId, String eje, String lineaAccion, String estado, String tipo, String responsable, String fechaInicio, String fechaFinal) {
        try {
            OportunidadMejora oportunidadMejora = oportunidadMejoraRepository.findById(hallazgoId).get();
            PlanMejoramiento planMejoramiento = planMejoramientoRepository.findById(planMejoramientoId).get();
            oportunidadMejora.setHallazgo(hallazgo);
            oportunidadMejora.setPlanMejoramientoId(planMejoramiento);
            Caracteristica caracteristica = caracteristicaRepository.findById(caracteristicaId).get();
            oportunidadMejora.setCaracteristicaId(caracteristica);
            oportunidadMejora.setEje(eje);
            oportunidadMejora.setLineaAccion(lineaAccion);
            oportunidadMejora.setEstado(estado);
            oportunidadMejora.setTipo(tipo);
            oportunidadMejora.setResponsable(responsable);
            oportunidadMejora.setFechaInicio(fechaInicio);
            oportunidadMejora.setFechaFin(fechaFinal);
            oportunidadMejoraRepository.saveAndFlush(oportunidadMejora);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar la oportunidad de mejora", e);
            throw e;
        }
    }

    @Override
    public void eliminarOportunidadMejora(Integer oportunidadMejoraId) {
        try {
            OportunidadMejora oportunidadMejora = oportunidadMejoraRepository.findById(oportunidadMejoraId).get();
            oportunidadMejoraRepository.delete(oportunidadMejora);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar eliminar la oportunidad de mejora:{} ", e);
        }
    }

}
