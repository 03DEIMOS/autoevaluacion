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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String variable1;

    private String variable2;

    private String estado;

    private String terminado;

    @Column(name = "ES_MUESTRA")
    private String esMuestra;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROCESO_ID")
    private Proceso proceso;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FUENTE_ID")
    private Fuente fuente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuarioId;

    public Persona(String variable1, String variable2, Proceso proceso, Fuente fuente, String estado,
            String terminado, String esMuestra, Usuario usuario) {
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.proceso = proceso;
        this.fuente = fuente;
        this.estado = estado;
        this.terminado = terminado;
        this.esMuestra = esMuestra;
        this.usuarioId = usuario;
    }
}
