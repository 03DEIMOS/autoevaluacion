/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.TipoPregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPreguntaRepository extends JpaRepository<TipoPregunta, Integer>{
    
}
