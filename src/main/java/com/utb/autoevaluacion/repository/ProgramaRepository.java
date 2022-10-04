/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Programa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer>{
    
    @Query("Select p from Programa p where p.nombre <> 'INSTITUCIONAL'")
    List<Programa> programasNoInstitucional();
    
    @Query("Select p from Programa p where p.nombre = 'INSTITUCIONAL'")
    Programa programaInstitucional();
    
    @Query("Select p from Programa p where p.facultadId.id = ?1")
    List<Programa> getProgramasByFacultad(Integer facultadId);
    
}
