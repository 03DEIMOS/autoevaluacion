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

    @Query(value = "SELECT * FROM persona WHERE usuario_id = ?1 AND terminado='N' AND estado = 'A' AND ES_MUESTRA='S'",
            nativeQuery = true)
    List<Persona> buscarPersonaPorUsuarioIdActivaYEsMuestra(Integer usuario);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional(readOnly = false)
    @Query(value = "UPDATE persona SET terminado='S' WHERE id = ?1 AND terminado='N' AND estado = 'A' AND ES_MUESTRA='S'",
            nativeQuery = true)
    void personaTerminaEncuesta(Integer personaId);

}
