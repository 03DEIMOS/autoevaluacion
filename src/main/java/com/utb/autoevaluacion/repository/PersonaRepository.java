/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    @Query(value = "SELECT * FROM persona WHERE usuario_id = ?1 AND estado = 'A' AND ES_MUESTRA='S'",
            nativeQuery = true)
    List<Persona> buscarPersonaPorUsuarioIdActivaYEsMuestra(Integer usuario);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional(readOnly = false)
    @Query(value = "UPDATE persona SET terminado='S' WHERE id = ?1 AND terminado='N' AND estado = 'A' AND ES_MUESTRA='S'",
            nativeQuery = true)
    void personaTerminaEncuesta(Integer personaId);
    
    @Query(value = "SELECT * FROM persona WHERE proceso_id = ?1 "
            + "AND fuente_id = ?2 AND estado = 'A' AND ES_MUESTRA='S' ", nativeQuery = true)
    List<Persona> buscarPoblacionPorProcesoYFuenteActivaYEsMuestra(Integer proceso, Integer fuente);
    
     @Query(value = "SELECT * FROM persona WHERE proceso_id = ?1 "
            + "AND fuente_id = ?2 AND usuario_id =?3 AND estado = 'A' ", nativeQuery = true)
    Persona buscarPersonaPorProcesoFuenteYUsuarioActiva(Integer proceso, Integer fuente, Integer usuario);
    
    @Query(value = "SELECT a.* FROM persona a INNER JOIN fuente b ON b.id = a.fuente_id"
            + " WHERE a.proceso_id= ?1 AND a.estado = 'A' AND a.es_muestra='S' "
            + " ORDER BY  b.nombre ASC",
            nativeQuery = true)
    List<Persona> muestraPorProceso(Integer procesoId);
    
    @Query(value = "SELECT * FROM persona WHERE proceso_id= ?1 AND estado = 'A' AND es_muestra='S' AND terminado='S' ORDER BY fuente_id ASC",
            nativeQuery = true)
    List<Persona> muestraPorProcesoEncuestaTerminada(Integer procesoId);
    
    @Query(value = "SELECT COUNT(*) FROM persona WHERE proceso_id= ?1 AND fuente_id= ?2 AND estado = 'A' AND es_muestra='S' ",
            nativeQuery = true)
    Integer cantidadTotalMuestraPorProcesoFuente(Integer procesoId, Integer fuenteId);
    
    @Query(value = "SELECT COUNT(*) FROM persona WHERE proceso_id= ?1 AND fuente_id= ?2 AND estado = 'A' AND es_muestra='S' AND terminado='S' ",
            nativeQuery = true)
    Integer cantidadMuestraEncuestaTerminadaPorProcesoFuente(Integer procesoId, Integer fuenteId);
    
}
