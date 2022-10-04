/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ASUS
 */
@Data
@Entity
@Table(name = "plan_mejoramiento")
public class PlanMejoramiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "plan")
    private String plan;

    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Programa programaId;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "fechaini")
    private String fechaInicio;

    @Column(name = "fechafin")
    private String fechaCierre;
    
    @Column(name="tipo_plan")
    private String tipoPlan;
}
