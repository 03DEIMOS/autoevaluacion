/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.TipoPregunta;
import com.utb.autoevaluacion.repository.TipoPreguntaRepository;
import com.utb.autoevaluacion.service.TipoPreguntaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TipoPreguntaServiceImpl implements TipoPreguntaService {
    
    @Autowired
    TipoPreguntaRepository tipoPreguntaRepository;
    
    @Override
    public TipoPregunta buscarTipoPregunta(Integer id) {
        TipoPregunta tipoPregunta = null;
        Optional<TipoPregunta> tipoPreguntaOptional = null;
        try {
            tipoPreguntaOptional = tipoPreguntaRepository.findById(id);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: ", e);
        }
        if(tipoPreguntaOptional.isPresent()){
            tipoPregunta = tipoPreguntaOptional.get();
        }
        return tipoPregunta;
    }

    @Override
    public List<TipoPregunta> buscarTipoPreguntas() {
        List<TipoPregunta> tipoPreguntas = null;
        try {
            tipoPreguntas = tipoPreguntaRepository.findAll();
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: ", e);
        }
        return tipoPreguntas;
    }

    @Override
    public void crearTipoPregunta(TipoPregunta tipoPregunta) {
        try {
            tipoPreguntaRepository.saveAndFlush(tipoPregunta);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: "+e, e);
        }
    }

    @Override
    public void eliminarTipoPregunta(Integer id) {
        try {
            tipoPreguntaRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: "+e, e);
        }
    }

    @Override
    public void actualizarTipoPregunta(TipoPregunta tipoPregunta) {
        try {
            tipoPreguntaRepository.saveAndFlush(tipoPregunta);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: ", e);
        }
    }
 }
