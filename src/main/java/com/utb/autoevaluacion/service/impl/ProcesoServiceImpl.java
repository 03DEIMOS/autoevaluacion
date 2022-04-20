/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;


import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.repository.ProcesoRepository;
import com.utb.autoevaluacion.service.ProcesoService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProcesoServiceImpl implements ProcesoService {

    @Autowired
    ProcesoRepository procesoRepository;
    
    @Override
    public Proceso buscarProceso(Integer id) {
        Proceso proceso = null;
        Optional<Proceso> procesoOptional = null;
        try {
            procesoOptional = procesoRepository.findById(id);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e, e);
        }
        if (procesoOptional.isPresent()) {
            proceso = procesoOptional.get();
        }
        return proceso;
    }

    @Override
    public List<Proceso> buscarProcesos() {
        List<Proceso> procesos = null;
        try {
            procesos = procesoRepository.findAll();
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e, e);
        }
        return procesos;
    }

    @Override
    public void crearProceso(Proceso proceso) {
        try {
            procesoRepository.saveAndFlush(proceso);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e, e);
        }
    }

    @Override
    public void cambiarEstadoProceso(Proceso proceso, String estado) {
        proceso.setEstado(estado);
        switch(estado)
        {
            case "En Ejecución": proceso.setFechaInicio(""+LocalDate.now());
            break;
            case "Finalizado": proceso.setFechaCierre(""+LocalDate.now());
            break;
        }        
        try {
            procesoRepository.saveAndFlush(proceso);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e, e);
        }
    }

 

    @Override
    public void actualizarProceso(Proceso proceso) {
        try {
            procesoRepository.saveAndFlush(proceso);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e, e);
        }
    }

    @Override
    public void eliminarProceso(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
