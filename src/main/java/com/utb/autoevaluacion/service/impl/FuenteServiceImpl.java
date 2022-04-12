
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.repository.FuenteRepository;
import com.utb.autoevaluacion.service.FuenteService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FuenteServiceImpl implements FuenteService{
    
    @Autowired
    FuenteRepository fuenteRepository;       
    
    @Override
    public List<Fuente> buscarFuentes() {
        return fuenteRepository.findAll();
    }

    @Override
    public Fuente buscarFuente(Integer fuenteId) {
        Fuente fuente = null;
        Optional<Fuente> fuenteOptional = null;
        try {
            fuenteOptional = fuenteRepository.findById(fuenteId);
            if (fuenteOptional.isPresent()) {
                fuente = fuenteOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado", e);
        }
        return fuente;
    }   
    
    @Override
    public List<Fuente> buscarFuentesXproceso(Integer procesoId) {
        List<Fuente> fuentes = null;
        try {
            fuentes = fuenteRepository.findFuentesByProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{} ", e);
        }
        return fuentes;
    }
    
     @Override
    public List<Fuente> buscarFuentesXmodeloXpregunta(Integer idModelo, Integer idPregunta) {
        List<Fuente> fuentes = null;
        try {
            fuentes = fuenteRepository.findByModeloPregunta(idModelo, idPregunta);
        } catch (Exception e) {
            log.error("Ha oscurrido un error inesperado:{} ", e);
        }
        return fuentes;
    }

    @Override
    public void crearFuente(String nombre, String descripcion) {
         Fuente fuente = new Fuente();

        fuente.setNombre(nombre);
        fuente.setDescripcion(descripcion);
        try {
            fuenteRepository.saveAndFlush(fuente);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al crear la fuente", e);
            throw e;
        }
    }

    @Override
    public void actualizarFuente(Integer fuenteId, String nombre, String descripcion) {
        try {
            Fuente fuente = fuenteRepository.findById(fuenteId).get();
            fuente.setNombre(nombre);
            fuente.setDescripcion(descripcion);
            fuenteRepository.saveAndFlush(fuente);
        } catch (Exception e) {
            log.info("Ha ocurrido un error al actualizar la fuente", e);
            throw e;
        }
    }
}
