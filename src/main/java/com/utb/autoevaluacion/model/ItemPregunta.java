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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@Table(name = "itempregunta")
public class ItemPregunta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;    
    
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "item")
    private String itemPregunta;
    
    @Column(name = "preguntaid", insertable = false, updatable = false)
    private Integer preguntaId;

   
    
    
}
