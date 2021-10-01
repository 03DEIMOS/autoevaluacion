/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.service.CaracteristicaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author DEIMOS
 */
@Service
public class CaracteristicaServiceImpl implements CaracteristicaService{
    @Override
    public List<Caracteristica> getCaracteristica() {
        Caracteristica c = new Caracteristica();
        c.setId(1);
        c.setCodigo("C01");
        c.setNombre("Caracteristica 1");
        List<Caracteristica> listCaracteristica = new ArrayList<>();
        
        listCaracteristica.add(c);
        return listCaracteristica;
    }
}
