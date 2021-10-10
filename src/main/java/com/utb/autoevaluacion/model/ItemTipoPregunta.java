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
import lombok.Data;

@Data
@Entity
@Table(name = "item_tipo_pregunta")
public class ItemTipoPregunta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "respuesta")
    private String respuesta;
    
    @Column(name = "valor")
    private String valor;
    
    @Column(name = "tipo_pregunta_id", insertable = false, updatable = false)
    private Integer tipoPreguntaId;

   
    
    
}
