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
@Table(name="pregunta")
public class Pregunta implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="codigo")
    private String codigo;
    
    @Column(name="pregunta")
    private String pregunta;
    
    @Column(name="tipo")
    private String tipo;
    
    @Column(name="pregunta_padre")
    private Integer preguntaPadre;
    
    @Column(name="repetir")
    private String repetir;

    public Pregunta() { }

}
