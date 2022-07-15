/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author DEIMOS
 */

@Data
@Entity
@Table(name="hallazgo")
public class OportunidadMejora implements Serializable {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idhallazgo")
    private Integer idHallazgo;
    
    @Column(name="hallazgo")
    private String hallazgo;
      
    @JoinColumn(name = "plan_mejoramiento_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private PlanMejoramiento planMejoramientoId;
    
    @JoinColumn(name = "caracteristica_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Caracteristica caracteristicaId;
    
    @Column(name="eje")
    private String eje;
    
    @Column(name="linea_accion")
    private String lineaAccion;
    
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoAccion estadoId;
    
    @Column(name="responsable")
    private String responsable;
    
    @Column(name="fecha_inicio")
    private String fechaInicio;
        
    @Column(name="fecha_fin")
    private String fechaFin;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="recurso")
    private String recurso;
    
    @Column(name="indicador")
    private String indicador;
    
    @Column(name="meta")
    private String meta;
    
    @Column(name="linea_base")
    private String lineaBase;
    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idseguimiento", referencedColumnName = "idHallazgo")
    private List<Seguimiento> seguimientos;
    
    public OportunidadMejora() {  } 
    
}
