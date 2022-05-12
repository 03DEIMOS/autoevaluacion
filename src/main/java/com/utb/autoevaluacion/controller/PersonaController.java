/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.service.PersonaService;
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
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/poblacion")
    public String buscarPoblacionPorProcesoYFuenteActivaYEsMuestra(@RequestParam Integer procesoId, @RequestParam Integer fuenteId, Model model) {
        List<Persona> personas = null;
        try {
            personas = personaService.buscarPoblacionPorProcesoYFuenteActivaYEsMuestra(procesoId, fuenteId);
        } catch (Exception e) {
            log.info("ha ocurrido un error buscando la poblacion:{} ", e);

        }
        boolean variablePresente2 = personas.stream()
                .filter(p -> p.getVariable2() != null && !p.getVariable2().isEmpty())
                .findAny().isPresent();
        model.addAttribute("personas", personas);
        model.addAttribute("variable2Exist", variablePresente2);
        model.addAttribute("proceso", procesoId);
        model.addAttribute("fuente", fuenteId);
        return "comitePrograma\\muestra\\selectorListMuestra";

    }

    @GetMapping("/crearEvaluador/{procesoId}/{fuenteId}")
    public String formularioCrearEvaluador(@PathVariable Integer procesoId, @PathVariable Integer fuenteId, Model model) {
        log.info("Ejecutanto metodo [formularioCrearEvaluador] ");
        model.addAttribute("procesoId", procesoId);
        model.addAttribute("fuenteId", fuenteId);
        return "comitePrograma\\muestra\\crearEvaluador";
    }

    @GetMapping("/editarEvaluador/{procesoId}/{fuenteId}/{personaId}")
    public String formularioCrearEvaluador(@PathVariable Integer procesoId, @PathVariable Integer fuenteId, @PathVariable Integer personaId, Model model) {
        log.info("Ejecutanto metodo [formularioCrearEvaluador] ");
        model.addAttribute("procesoId", procesoId);
        model.addAttribute("fuenteId", fuenteId);
        Persona persona = personaService.buscarPersona(personaId);
        model.addAttribute("persona", persona);
        return "comitePrograma\\muestra\\editarEvaluador";
    }

    @PostMapping("/crearEvaluador")
    public ResponseEntity<?> crearEvaluador(@RequestParam String identificacion, @RequestParam String codigo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String correo, @RequestParam String variable1, @RequestParam String variable2, @RequestParam Integer fuenteId, @RequestParam Integer procesoId) {
        log.info("Ejecutanto metodo [crearEvaluador] identificacion:{}, codigo:{}, nombre:{}, apellido:{}, correo:{}, variable1:{}, variable2:{}, fuenteId:{}, procesoId:{}",
                identificacion, codigo, nombre, apellido, correo, variable1, variable2, fuenteId, procesoId);
        HttpStatus status;
        try {
            personaService.crearEvaluador(identificacion, codigo, nombre, apellido, correo, variable1, variable2, fuenteId, procesoId);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }
    
    @PutMapping(value = "/editarEvaluador")
    public ResponseEntity<?> editarEvaluador(@RequestParam Integer personaId, @RequestParam String identificacion, @RequestParam String codigo, @RequestParam String nombre, @RequestParam String apellido,
            @RequestParam String correo, @RequestParam String variable1, @RequestParam String variable2, @RequestParam Integer fuenteId, @RequestParam Integer procesoId) {

        log.info("Ejecutanto metodo [editarEvaluador] personaId{}, identificacion:{}, codigo:{}, nombre:{}, apellido:{}, correo:{}, variable1:{}, variable2:{}, fuenteId:{}, procesoId:{}",
                personaId, identificacion, codigo, nombre, apellido, correo, variable1, variable2, fuenteId, procesoId);
        HttpStatus status;
        try {
            personaService.actualizarEvaluador(personaId, identificacion, codigo, nombre, apellido, correo, variable1, variable2, fuenteId, procesoId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);

    }

}
