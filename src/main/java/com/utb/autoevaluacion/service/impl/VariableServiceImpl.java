/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.model.Variable;
import com.utb.autoevaluacion.repository.VariableRepository;
import com.utb.autoevaluacion.service.VariableService;
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
public class VariableServiceImpl implements VariableService {

    @Autowired
    VariableRepository variableRepository;

    @Override
    public List<Variable> getVariables() {
        return variableRepository.findAll();
    }

    @Override
    public Variable getVariableById(Integer id) {
        return variableRepository.getOne(id);
    }

    @Override
    public void crearVariable(String llave, String valor) {
        Variable variable = new Variable();
        variable.setLlave(llave);
        variable.setValor(valor);
        try {
            variableRepository.saveAndFlush(variable);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al crear la variable: {}", e);
            throw e;
        }
    }

    @Override
    public void actualizarVariable(Integer variableId, String llave, String valor) {
        try {
            Variable variable = variableRepository.findById(variableId).get();
            variable.setLlave(llave);
            variable.setValor(valor);
            variableRepository.saveAndFlush(variable);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar la variable error:{}", e);
            throw e;
        }
    }

    @Override
    public Variable getVariableByLlave(String llave) {
        try {
            return variableRepository.findByLlave(llave);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al buscar la variable con llave:{}, error:{}", llave, e);
            throw e;
        }
    }

}
