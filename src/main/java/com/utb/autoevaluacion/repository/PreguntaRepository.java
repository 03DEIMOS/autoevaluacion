/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Pregunta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DEIMOS
 */
@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {

    @Query(value = "SELECT p.* FROM pregunta p "
            + "INNER JOIN encuestahaspregunta ep on ep.pregunta_id = p.id "
            + "INNER JOIN encuesta e on e.id = ep.encuesta_id "
            + "INNER JOIN modelo m on m.id = e.modelo_id "
            + "INNER JOIN proceso pr on pr.modelo_id = m.id "
            + "WHERE pr.id = ?1 "
            + "group by p.id",
            nativeQuery = true)
    List<Pregunta> buscarPreguntasPorProceso(Integer procesoId);

    @Query(value = "SELECT p.* FROM pregunta p "
            + "INNER JOIN encuestahaspregunta ep on ep.pregunta_id = p.id "
            + "INNER JOIN encuesta e on e.id = ep.encuesta_id "
            + "INNER JOIN modelo m on m.id = e.modelo_id "
            + "INNER JOIN proceso pr on pr.modelo_id = m.id "
            + "WHERE pr.id = ?1 and e.fuente_id = ?2 "
            + "group by p.id",
            nativeQuery = true)
    List<Pregunta> buscarPreguntasPorProcesoyPublico(Integer procesoId, Integer fuenteId);
}
