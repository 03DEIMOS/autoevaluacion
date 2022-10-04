/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Programa;
import com.utb.autoevaluacion.service.FacultadService;
import com.utb.autoevaluacion.service.ProgramaService;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author ASUS
 */
@Slf4j
@Controller
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @Autowired
    private FacultadService facultadService;

    @GetMapping("/programas")
    public String modelos(Model model) {
        model.addAttribute("listaPro", programaService.getProgramas());
        return "comiteCentral\\programa\\listar";
    }
    
     @GetMapping("/programaInstitucional")
    public String programaInstitucional(Model model) {
        Programa programaInstitucional = programaService.buscarProgramaInstitucional();
        List<Programa> list = new ArrayList<>();
        list.add(programaInstitucional);
        model.addAttribute("listaP", list);
        return "comiteCentral\\programa\\listEmbedded";
    }

    @GetMapping("/programasByFacultad/{facultadId}")
    public String programasByFacultad(@PathVariable Integer facultadId, Model model) {
        model.addAttribute("listaP", programaService.getProgramasByFacultad(facultadId));               
        return "comiteCentral\\programa\\listEmbedded";

    }
    
    
    @GetMapping("/crear")
    public String formularioCrearPrograma(Model model) {
        log.info("Ejecutanto metodo [formularioCrearPrograma] ");
        model.addAttribute("listaFac", facultadService.getFacultades());
        return "comiteCentral\\programa\\crear";
    }

    @GetMapping("/editar/{programaId}")
    public String formularioEditarPrograma(@PathVariable Integer programaId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarPrograma] programaId:{} ", programaId);
        Programa programa = programaService.buscarPrograma(programaId);
        model.addAttribute("listaFac", facultadService.getFacultades());
        model.addAttribute("programa", programa);
        return "comiteCentral\\programa\\editar";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearPrograma(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String modalidad, @RequestParam String tipoformacion, @RequestParam Integer facultadId) {

        log.info("Ejecutanto metodo [crearPrograma] nombre:{}, descripcion:{}, modalidad:{}, tipoformacion:{}, facultadId:{} ", nombre, descripcion, modalidad, tipoformacion, facultadId);
        HttpStatus status;
        try {
            programaService.crearPrograma(nombre, descripcion, modalidad, tipoformacion, facultadId);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: ", e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarPrograma(@RequestParam Integer programaId, @RequestParam String nombre, @RequestParam String descripcion, @RequestParam String modalidad, @RequestParam String tipoformacion, @RequestParam Integer facultadId) {

        log.info("Ejecutanto metodo [editarPrograma] programaId:{}, nombre:{}, descripcion:{}, modalidad:{}, tipoformacion:{}, facultadId:{} ", programaId, nombre, descripcion, modalidad, tipoformacion, facultadId);
        HttpStatus status;
        try {
            programaService.actualizarPrograma(programaId, nombre, descripcion, modalidad, tipoformacion, facultadId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " , e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);

    }
}
