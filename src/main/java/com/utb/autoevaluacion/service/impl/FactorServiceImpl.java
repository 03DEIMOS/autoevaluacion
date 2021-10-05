package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.repository.FactorRepository;
import com.utb.autoevaluacion.service.FactorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactorServiceImpl implements FactorService{
    
    @Autowired
    FactorRepository factorRepository;
    
     @Override
    public List<Factor> getFactores() {
        return factorRepository.findAll();
    }

}
