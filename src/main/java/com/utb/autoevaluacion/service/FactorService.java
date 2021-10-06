package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Factor;
import java.util.List;

public interface FactorService {
    List<Factor> getFactores();
    
    List<Factor> getFactoresByModelo(Integer modeloId);
    
}
