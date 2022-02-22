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
        + " WHERE b.proceso_id= ?1 AND a.item_pregunta_id = ?2 AND b.fuente_id = ?3"
        + " AND b.variable1 = ?4 AND b.variable2 = ?5"
        + " ORDER BY  a.item_pregunta_id ASC",
        nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoItemPreguntaFuenteyVariables(Integer procesoId, Integer itemPreguntaId, Integer fuenteId, String variable1, String variable2);
    
    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
        + " WHERE b.proceso_id= ?1 AND a.item_pregunta_id = ?2 AND b.fuente_id = ?3"
        + " AND b.variable1 = ?4"
        + " ORDER BY  a.item_pregunta_id ASC",
        nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoItemPreguntaFuenteyVariable1(Integer procesoId, Integer itemPreguntaId, Integer fuenteId, String variable1);
    
    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
        + " WHERE b.proceso_id= ?1 AND a.item_pregunta_id = ?2 AND b.fuente_id = ?3"
        + " AND b.variable2 = ?4"
        + " ORDER BY  a.item_pregunta_id ASC",
        nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoItemPreguntaFuenteyVariable2(Integer procesoId, Integer itemPreguntaId, Integer fuenteId, String variable2);

    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 AND b.fuente_id = ?3"
            + " AND a.item_pregunta_id IS NULL ORDER BY  a.pregunta_id ASC",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoPreguntaFuente(Integer procesoId, Integer preguntaId, Integer fuenteId);
    
    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 AND b.fuente_id = ?3"
            + " AND b.variable1 = ?4 AND b.variable2 = ?5"
            + " AND a.item_pregunta_id IS NULL ORDER BY  a.pregunta_id ASC",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoPreguntaFuenteyVariables(Integer procesoId, Integer preguntaId, Integer fuenteId, String variable1, String variable2);
    
    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 AND b.fuente_id = ?3"
            + " AND b.variable1 = ?4"
            + " AND a.item_pregunta_id IS NULL ORDER BY  a.pregunta_id ASC",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoPreguntaFuenteyVariable1(Integer procesoId, Integer preguntaId, Integer fuenteId, String variable1);

    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 AND b.fuente_id = ?3"
            + " AND b.variable2 = ?4"
            + " AND a.item_pregunta_id IS NULL ORDER BY  a.pregunta_id ASC",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoPreguntaFuenteyVariable2(Integer procesoId, Integer preguntaId, Integer fuenteId, String variable2);
    
    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.item_pregunta_id = ?2  AND a.respuesta = ?3 ",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoItemPreguntaRespuesta(Integer procesoId, Integer itemPreguntaId, Integer respuesta);
    
    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2  AND a.respuesta = ?3 ",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoPreguntaRespuesta(Integer procesoId, Integer preguntaId, Integer respuesta);
    
    @Query(value = "SELECT COUNT(*) FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 ",
            nativeQuery = true)
    Integer findTotalPersonasContestaronPregunta(Integer procesoId, Integer preguntaId);
    
    @Query(value = "SELECT COUNT(*) FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id "
            + "WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 AND a.item_pregunta_id = ?3 ",
            nativeQuery = true)
    Integer findTotalPersonasContestaronPreguntaItemPregunta(Integer procesoId, Integer preguntaId, Integer itemPreguntaId );
    
    @Query(value = "SELECT COUNT(*) FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id "
            + "WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 AND a.item_pregunta_id = ?3 and b.fuente_id = ?4 ",
            nativeQuery = true)
    Integer findTotalPersonasContestaronPreguntaItemPreguntaFuente(Integer procesoId, Integer preguntaId, Integer itemPreguntaId, Integer fuenteId);
    
    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.item_pregunta_id = ?2  AND a.respuesta = ?3 and b.fuente_id = ?4 ",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoItemPreguntaRespuestaFuente(Integer procesoId, Integer itemPreguntaId, Integer respuesta, Integer fuenteId);
    
    
    @Query(value = "SELECT a.* FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id"
            + " WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2  AND a.respuesta = ?3 and b.fuente_id = ?4 ",
            nativeQuery = true)
    List<ResultadoEvaluacion> findByProcesoPreguntaRespuestaFuente(Integer procesoId, Integer preguntaId, Integer respuesta, Integer fuenteId);
    
    @Query(value = "SELECT COUNT(*) FROM resultadoevaluacion a INNER JOIN persona b ON b.id = a.persona_id WHERE b.proceso_id= ?1 AND a.pregunta_id = ?2 AND fuente_id = ?3 ",
            nativeQuery = true)
    Integer findTotalPersonasContestaronPreguntaFuente(Integer procesoId, Integer preguntaId, Integer fuenteId);
}
