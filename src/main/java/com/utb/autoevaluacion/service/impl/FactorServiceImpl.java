package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.service.FactorService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FactorServiceImpl implements FactorService{
    
    @Override
    public List<Factor> getFactores() {
        Factor f = new Factor();
        f.setId(1);
        f.setCodigo("ABC");
        List<Factor> listFactor = new ArrayList<>();
        
        listFactor.add(f);
        return listFactor;
    }
}
