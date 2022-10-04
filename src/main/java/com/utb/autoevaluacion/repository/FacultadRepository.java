/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Facultad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Integer>{
    
    @Query("Select f from Facultad f where f.nombre <> 'INSTITUCIONAL'")
    List<Facultad> getFacultadesNoInstitucional();
}
