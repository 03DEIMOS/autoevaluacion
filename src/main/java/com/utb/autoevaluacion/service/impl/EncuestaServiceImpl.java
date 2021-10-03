/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.service.EncuestaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEIMOS
 */
@Service
public class EncuestaServiceImpl implements EncuestaService {
    @Override
    public List<Encuesta> getEncuestas() {
        Encuesta e = new Encuesta();
        e.setId(1);
        e.setCodigo("E01");
        e.setNombre("Encuesta 1");
        List<Encuesta> listEncuesta = new ArrayList<>();
        
        listEncuesta.add(e);
        return listEncuesta;
    }
}
