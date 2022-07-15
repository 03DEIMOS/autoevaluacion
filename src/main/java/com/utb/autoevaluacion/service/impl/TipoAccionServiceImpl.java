/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.model.TipoAccion;
import com.utb.autoevaluacion.model.Variable;
import com.utb.autoevaluacion.repository.TipoAccionRepository;
import com.utb.autoevaluacion.repository.VariableRepository;
import com.utb.autoevaluacion.service.TipoAccionService;
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
public class TipoAccionServiceImpl implements TipoAccionService {

    @Autowired
    TipoAccionRepository tipoAccionRepository;

    @Override
    public List<TipoAccion> getTiposAccion() {
        return tipoAccionRepository.findAll();
    }

    @Override
    public TipoAccion getTipoAccionById(Integer id) {
         return tipoAccionRepository.getOne(id);
    }

    @Override
    public void crearTipoAccion(TipoAccion tipoAccion) {
        try {
            tipoAccionRepository.saveAndFlush(tipoAccion);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
            throw e;
        }
    }

    @Override
    public void actualizarTipoAccion(TipoAccion tipoAccion) {
        try {
            tipoAccionRepository.saveAndFlush(tipoAccion);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
            throw e;
        }
    }

}
