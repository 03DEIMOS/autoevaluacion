/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Factor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DEIMOS
 */

@Repository
public interface FactorRepository extends JpaRepository<Factor, Integer>{
    
    @Query("Select f from Factor f where f.modeloId.id=?1")
    List<Factor> findFactorByModeloId(Integer modeloId);
    
    @Query("SELECT f FROM Factor f "
            + "INNER JOIN Caracteristica c ON c.factorId.id = f.id "
            + "WHERE f.modeloId.id = ?1 and size(c.preguntaList) > 0 "
            + "GROUP BY f.id")
    List<Factor> buscarPorModeloYConPreguntasAsociadas(Integer modeloId);
}