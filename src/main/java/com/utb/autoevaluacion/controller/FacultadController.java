/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Facultad;
import com.utb.autoevaluacion.service.FacultadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/facultad")
public class FacultadController {
    
    @Autowired
    private FacultadService facultadService;
    
    @GetMapping("/facultades")
    public String modelos(Model model) {
        model.addAttribute("listaFacultades", facultadService.getFacultades());
        return "comiteCentral\\facultad\\listar";

    }
    
    @GetMapping("/crear")
    public String formularioCrearFacultad(Model model) {
        log.info("Ejecutanto metodo [formularioCrearFacultad] ");
        return "comiteCentral\\facultad\\crear";
    }

    @GetMapping("/editar/{facultadId}")
    public String formularioEditarFacultad(@PathVariable Integer facultadId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarFacultad] facultadId:{} ", facultadId);
        Facultad facultad = facultadService.buscarFacultad(facultadId);
        model.addAttribute("facultad", facultad);
        return "comiteCentral\\facultad\\editar";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearFacultad(@RequestParam String nombre, @RequestParam String descripcion) {

        log.info("Ejecutanto metodo [crearFacultad] nombre:{}, descripcion:{} ", nombre, descripcion);
        HttpStatus status;
        try {
            facultadService.crearFacultad(nombre, descripcion);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarFacultad(@RequestParam Integer facultadId, @RequestParam String nombre, @RequestParam String descripcion) {

        log.info("Ejecutanto metodo [editarFacultad] facultadId:{}, nombre:{}, descripcion:{} ", facultadId, nombre, descripcion);
        HttpStatus status;
        try {
            facultadService.actualizarFacultad(facultadId, nombre, descripcion);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);

    }
}
