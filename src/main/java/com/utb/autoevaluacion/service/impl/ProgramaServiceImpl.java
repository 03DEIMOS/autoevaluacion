/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Facultad;
import com.utb.autoevaluacion.model.Programa;
import com.utb.autoevaluacion.repository.FacultadRepository;
import com.utb.autoevaluacion.repository.ProgramaRepository;
import com.utb.autoevaluacion.service.ProgramaService;
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
public class ProgramaServiceImpl implements ProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    @Override
    public List<Programa> getProgramas() {
        return programaRepository.findAll();
    }

    @Override
    public void crearPrograma(String nombre, String descripcion, String modalidad, String tipoformacion, Integer facultadId) {
        try {
            Programa programa = new Programa();
            programa.setNombre(nombre);
            programa.setDescripcion(descripcion);
            programa.setTipoFormacion(tipoformacion);
            programa.setModalidad(modalidad);
            Facultad facultad = facultadRepository.findById(facultadId).get();
            programa.setFacultadId(facultad);
            programaRepository.saveAndFlush(programa);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear el programa", e);
            throw e;
        }
    }

    @Override
    public void actualizarPrograma(Integer programaId, String nombre, String descripcion, String modalidad, String tipoformacion, Integer facultadId) {
        try {
            Programa programa = programaRepository.findById(programaId).get();
            programa.setNombre(nombre);
            programa.setDescripcion(descripcion);
            programa.setTipoFormacion(tipoformacion);
            programa.setModalidad(modalidad);
            Facultad facultad = facultadRepository.findById(facultadId).get();
            programa.setFacultadId(facultad);
            programaRepository.saveAndFlush(programa);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al actualizar el programa", e);
            throw e;
        }
    }

    @Override
    public Programa buscarPrograma(Integer programaId) {
        Programa programa = null;
        Optional<Programa> programaOptional = null;
        try {
            programaOptional = programaRepository.findById(programaId);
            if (programaOptional.isPresent()) {
                programa = programaOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado", e);
        }
        return programa;
    }

    @Override
    public List<Programa> getProgramasNoInstitucional() {
        return programaRepository.programasNoInstitucional();
    }

    @Override
    public Programa buscarProgramaInstitucional() {
        return programaRepository.programaInstitucional();
    }

    @Override
    public List<Programa> getProgramasByFacultad(Integer facultadId) {
       return programaRepository.getProgramasByFacultad(facultadId);
    }

}
