/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Fuente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface FuenteRepository extends JpaRepository<Fuente, Integer>{
    
    @Query(value = "select d.* from proceso a "
            + "inner join modelo b on b.id = a.modelo_id "
            + "inner join encuesta c on c.modelo_id = b.id "
            + "inner join fuente d on d.id = c.fuente_id "
            + "where a.id = ?1",
            nativeQuery = true)
    List<Fuente> findFuentesByProceso(Integer procesoId);
    
    @Query(value = "select c.* from encuesta a "
            + "inner join encuestahaspregunta b on b.encuesta_id = a.id "
            + "inner join fuente c on c.id = a.fuente_id "
            + "where a.modelo_id = ?1 and b.pregunta_id = ?2",
            nativeQuery = true)
    List<Fuente> findByModeloPregunta(Integer modeloId, Integer preguntaId);
}
