/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author DEIMOS
 */
@Data
@Entity
@Table(name="seguimiento")
public class Seguimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idseguimiento")
    private Integer idSeguimiento;
      
    @JoinColumn(name = "oportunidad_mejora_id", referencedColumnName = "idhallazgo")
    @ManyToOne(fetch = FetchType.EAGER)
    private OportunidadMejora oportunidadMejora;
    
    @Column(name="fecha_realizado")
    private String fechaRealizado;
    
    @Column(name="estado")
    private String estado;
    
    @Column(name="avances")
    private String avances;
    
    public Seguimiento (){}
}
