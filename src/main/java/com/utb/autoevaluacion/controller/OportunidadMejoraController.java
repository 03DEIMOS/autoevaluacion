package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.service.CaracteristicaService;
import com.utb.autoevaluacion.service.OportunidadMejoraService;
import com.utb.autoevaluacion.service.ProcesoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DEIMOS
 */

@Slf4j
@Controller
@RequestMapping("/oportunidadMejora")

public class OportunidadMejoraController {
    
    @Autowired
    private ProcesoService procesoService;
    
    @Autowired
    private CaracteristicaService caracteristicaService;
    
    @Autowired
    private OportunidadMejoraService oportunidadMejoraService;

    @GetMapping("/oportunidadesMejora/{procesoId}")
    public String oportunidadesMejora(@PathVariable Integer procesoId, Model model) {
        model.addAttribute("listaO", oportunidadMejoraService.getOportunidadMejoraByProceso(procesoId));
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\listar";

    }
    
    @GetMapping("/crear/{procesoId}")
    public String formularioCrearOportunidadMejora(@PathVariable Integer procesoId, Model model) {
        log.info("Ejecutanto método [formularioCrearOportunidadMejora] procesoId:{} ", procesoId);
        model.addAttribute("procesoId", procesoId);
        model.addAttribute("listaC", caracteristicaService.getCaracteristicas());
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\crear";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearOportunidadMejora(@RequestParam String hallazgo, @RequestParam Integer procesoId, @RequestParam Integer caracteristicaId, @RequestParam String eje, @RequestParam String linea_accion, @RequestParam String estado, @RequestParam String responsable) {

        log.info("Ejecutanto metodo [crearOportunidadMejora] caracteristicaId:{}, nombre:{}, ejeEstrategico:{}, lineaAccion:{}, estado:{}, responsable:{} ", hallazgo, procesoId, caracteristicaId, eje, linea_accion, estado, responsable);
        HttpStatus status;
        try {
            
            oportunidadMejoraService.crearOpotunidadMejora(hallazgo, procesoId, caracteristicaId, eje, linea_accion, estado, responsable);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }


}
