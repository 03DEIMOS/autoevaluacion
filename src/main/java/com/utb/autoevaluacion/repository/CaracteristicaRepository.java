/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.Modelo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DEIMOS
 */

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer>{
    
    @Query("Select c from Caracteristica c where c.factorId.modeloId.id=?1")
    List<Caracteristica> findCaracteristicasByModeloId(Integer modeloId);
    
    @Query("SELECT c FROM Caracteristica c LEFT JOIN FETCH c.factorId WHERE "
            + "c.modeloId = :modelo and size(c.preguntaList) > 0 ")
    List<Caracteristica> buscarPorModeloYConPreguntasAsociadas(@Param("modelo") Modelo modelo);
}