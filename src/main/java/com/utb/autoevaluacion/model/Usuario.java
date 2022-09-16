/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.model;

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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String usuario;

    private String identificacion;

    private String nombre;

    private String apellido;

    private String email;
    
    private String estado;
    
    @Column(name = "ENABLED")
    private Boolean enabled;
    
    private String contrasena;
    
    private String tipo;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "programa_has_usuario",
            joinColumns = { @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "programa_id", referencedColumnName = "id", nullable = false) })
    private List<Programa> programaList;
    

     public Usuario(String usuario, String identificacion, String nombre, String apellido,
            String email, String estado, boolean enabled) {
        this.usuario = usuario;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.estado = estado;
        this.enabled = enabled;
    }

}
