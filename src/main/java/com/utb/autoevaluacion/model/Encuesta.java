package com.utb.autoevaluacion.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="encuesta")
public class Encuesta  implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="codigo")
    private String codigo;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="objetivo")
    private String objetivo;
    
    @Column(name="instrucciones")
    private String instrucciones;
    
    @Column(name="version")
    private String version;
    
    @Column(name="fecha")
    private String fecha;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "encuestahaspregunta",
            joinColumns = { @JoinColumn(name = "encuesta_id", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "pregunta_id", referencedColumnName = "id", nullable = false) })
    private List<Pregunta> preguntaList;

    public Encuesta() {
    }

}
