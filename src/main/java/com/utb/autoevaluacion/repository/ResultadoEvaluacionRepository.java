/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.ResultadoEvaluacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface ResultadoEvaluacionRepository extends JpaRepository<ResultadoEvaluacion, Integer> {

    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.item_pregunta_id = ?2 AND b.fuente_id = ?3"
            + " ORDER BY  a.item_pregunta_id ASC",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoItemPreguntaFuente(Integer procesoId, Integer itemPreguntaId, Integer fuenteId);

    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 AND b.fuente_id = ?3"
            + " AND a.item_pregunta_id IS NULL ORDER BY  a.pregunta_id ASC",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoPreguntaFuente(Integer procesoId, Integer preguntaId, Integer fuenteId);
}
