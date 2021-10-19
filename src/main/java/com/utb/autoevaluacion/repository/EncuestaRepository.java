/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Encuesta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DEIMOS
 */

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Integer>{
    @Query("Select e from Encuesta e")
    List<Encuesta> findEncuestasByModeloId(Integer modeloId);
    
    @Query(value = "SELECT * FROM encuesta WHERE modelo_id = ?1 AND fuente_id = ?2 LIMIT 1",
            nativeQuery = true)
    Encuesta buscarEncuestaPorPersona(Integer modeloId, Integer fuenteId);
}