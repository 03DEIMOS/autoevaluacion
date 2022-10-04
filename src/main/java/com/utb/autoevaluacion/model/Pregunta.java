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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

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
    
    @Column(name="repetir")
    private String repetir;
    
    @Column(name="disenio")
    private String disenio;
    
    @Column(name="tipo_proceso")
    private String tipoProceso;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "preguntaid", referencedColumnName = "id", nullable = false)
    private List<ItemPregunta> itemPreguntas;
    
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo")
    private TipoPregunta tipoPregunta;
    
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "preguntaList")
    private List<Caracteristica> caracteristicaList;

    public Pregunta() { }

}
