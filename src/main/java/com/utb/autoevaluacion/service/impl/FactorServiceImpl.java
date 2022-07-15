package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.repository.FactorRepository;
import com.utb.autoevaluacion.repository.ModeloRepository;
import com.utb.autoevaluacion.service.FactorService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FactorServiceImpl implements FactorService {

    @Autowired
    FactorRepository factorRepository;

    @Autowired
    ModeloRepository modeloRepository;

    @Override
    public List<Factor> getFactores() {
        return factorRepository.findAll();
    }

    @Override
    public List<Factor> getFactoresByModelo(Integer m) {
        return factorRepository.findFactorByModeloId(m);
    }

    @Override
    public void crearFactor(String codigo, String nombre, Integer modeloId) {
        try {
            Factor factor = new Factor();
            factor.setCodigo(codigo);
            factor.setNombre(nombre);
            Modelo modelo = modeloRepository.findById(modeloId).get();
            factor.setModeloId(modelo);
            factorRepository.saveAndFlush(factor);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al crear el factor" + e);
            throw e;
        }
    }

    @Override
    public void actualizarFactor(Integer factorId, String codigo, String nombre, Integer modeloId) {
        try {
            Factor factor = new Factor();
            factor.setId(factorId);
            factor.setCodigo(codigo);
            factor.setNombre(nombre);
            Modelo modelo = modeloRepository.findById(modeloId).get();
            factor.setModeloId(modelo);
            factorRepository.saveAndFlush(factor);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar el factor" + e);
            throw e;
        }
    }

    @Override
    public Factor buscarFactor(Integer id) {
        Factor factor = null;
        Optional<Factor> factorOptional = null;
        try {
            factorOptional = factorRepository.findById(id);
            if (factorOptional.isPresent()) {
                factor = factorOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado" + e, e);
        }
        return factor;
    }

    @Override
    public List<Factor> buscarPorModeloYConPreguntasAsociadas(Integer modeloId) {
        List<Factor> factores = factorRepository.buscarPorModeloYConPreguntasAsociadas(modeloId);
        return factores;
    }

    @Override
    public List<Factor> getFactoresByPlanMejora(Integer planMejoraId) {
        List<Factor> factores = factorRepository.findByPlanMejora(planMejoraId);
        return factores;
    }
}
