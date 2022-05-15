/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.OportunidadMejora;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DEIMOS
 */
@Repository
public interface OportunidadMejoraRepository extends JpaRepository<OportunidadMejora, Integer>{
    @Query("Select h from OportunidadMejora h where h.procesoId.id=?1")
    List<OportunidadMejora> findOportunidadMejoraByProcesoId(Integer procesoId);
    
}
