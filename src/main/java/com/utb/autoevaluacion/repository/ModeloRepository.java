/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.repository;

import com.utb.autoevaluacion.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer>{
    
  /*  @Modifying
    @Query(value = "insert into modelo (nombre, descripcion) values (:nombre, :descripcion)", nativeQuery = true)
    void insertModelo(@Param("nombre") String nombre, @Param("descripcion") String descripcion );
    
    
    @Modifying
    @Query("update modelo m set m.nombre = :nombre, m.descripcion = :descripcion where m.id = :id")
        int updateModeloById(@Param("id") Integer id, 
         @Param("nombre") String nombre, @Param("descipcion") String descripcion);*/
}
