package com.utb.autoevaluacion.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="caracteristica")
public class Caracteristica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="codigo")
    private String codigo;
    
    @Column(name="nombre")
    private String nombre;
    
    @JoinColumn(name = "factor_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Factor factorId;
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pregunta_has_caracteristica",
            joinColumns = {
                @JoinColumn(name = "caracteristica_id", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "pregunta_id", referencedColumnName = "id", nullable = false)})
    private List<Pregunta> preguntaList;

    public Caracteristica() {  } 
}
