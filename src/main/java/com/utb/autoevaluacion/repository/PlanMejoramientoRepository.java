/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.PlanMejoramiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface PlanMejoramientoRepository extends JpaRepository<PlanMejoramiento, Integer> {

    @Query(value = "select a.* from plan_mejoramiento a "
            + "inner join programa b on b.id = a.programa_id "
            + "where b.nombre = 'INSTITUCIONAL'",
            nativeQuery = true)
    List<PlanMejoramiento> findInstitucionales();

}
