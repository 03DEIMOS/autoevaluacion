/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Seguimiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DEIMOS
 */
@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer>{
    
    @Query("Select h from Seguimiento h where h.oportunidadMejora.idHallazgo=?1")
    List<Seguimiento> findSeguimientoByOportunidadMejoraId(Integer idHallazgo);
    
}
