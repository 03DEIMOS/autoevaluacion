/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Facultad;
import com.utb.autoevaluacion.repository.FacultadRepository;
import com.utb.autoevaluacion.service.FacultadService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Slf4j
@Service
public class FacultadServiceImpl implements FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    @Override
    public List<Facultad> getFacultades() {
        return facultadRepository.findAll();
    }

    @Override
    public void crearFacultad(String nombre, String descripcion) {
        Facultad facultad = new Facultad();

        facultad.setNombre(nombre);
        facultad.setDescripcion(descripcion);
        try {
            facultadRepository.saveAndFlush(facultad);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear la facultad", e);
            throw e;
        }
    }

    @Override
    public void actualizarFacultad(Integer facultadId, String nombre, String descripcion) {
        try {
            Facultad facultad = facultadRepository.findById(facultadId).get();
            facultad.setNombre(nombre);
            facultad.setDescripcion(descripcion);
            facultadRepository.saveAndFlush(facultad);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al actualizar la facultad", e);
            throw e;
        }
    }

    @Override
    public Facultad buscarFacultad(Integer facultadId) {
        Facultad facultad = null;
        Optional<Facultad> facultadOptional = null;
        try {
            facultadOptional = facultadRepository.findById(facultadId);
            if (facultadOptional.isPresent()) {
                facultad = facultadOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado", e);
        }
        return facultad;
    }

}
