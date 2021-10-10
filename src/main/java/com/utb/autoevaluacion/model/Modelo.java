package com.utb.autoevaluacion.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="modelo")
public class Modelo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
     /*@Column(name="fechaactualizacion")
    private Integer fechaactualizacion;
    @Column(name="fechacreacion")
    private Date fechacreacion;
    @Column(name="observaciones")
    private String observaciones;*/

    public Modelo() {
    }

    
}
