/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.model.Seguimiento;
import com.utb.autoevaluacion.service.OportunidadMejoraService;
import com.utb.autoevaluacion.service.SeguimientoService;
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

/**
 *
 * @author DEIMOS
 */
@Slf4j
@Controller
@RequestMapping("/seguimiento")
public class SeguimientoController {
    
    @Autowired
    private SeguimientoService seguimientoService;
    
    @Autowired
    private OportunidadMejoraService oportunidadMejoraService;
    
    @GetMapping("/seguimientos/{idHallazgo}")
    public String oportunidadesMejora(@PathVariable Integer idHallazgo, Model model) {
        model.addAttribute("listaS", seguimientoService.getSeguimientoByOportunidadMejora(idHallazgo));
        return "comitePrograma\\proceso\\planMejoramiento\\seguimiento\\listar";

    }
    
    @GetMapping("/crear/{idHallazgo}")
    public String formularioCrearSeguimiento(@PathVariable Integer idHallazgo, Model model) {
        log.info("Ejecutanto método [formularioCrearSeguimiento] idHallazgo:{} ", idHallazgo);
        OportunidadMejora oportunidadMejora = oportunidadMejoraService.buscarOportunidadMejora(idHallazgo);
        model.addAttribute("idHallazgo", idHallazgo);
        //model.addAttribute("listaS", caracteristicaService.getCaracteristicasByModelo(proceso.getModeloId().getId()));
        return "comitePrograma\\proceso\\planMejoramiento\\seguimiento\\crear";
    }
    
    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearSeguimiento(@RequestParam String fechaProgramada, @RequestParam String fechaRealizado, 
            @RequestParam Integer porcentajeAvance, @RequestParam String avances, @RequestParam Integer idHallazgo) {

        log.info("Ejecutanto metodo [crearSeguimiento] fechaProgramada:{}, fechaRealizado:{}, porcentajeAvance:{}, avances:{}, idHallazgo:{} ", fechaProgramada, fechaRealizado, porcentajeAvance, avances, idHallazgo);
        HttpStatus status;
        try {
            
            seguimientoService.crearSeguimiento(fechaProgramada, fechaRealizado, porcentajeAvance, avances, idHallazgo);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " , e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }
    
    @GetMapping("/editar/{idSeguimiento}/{idHallazgo}")
    public String formularioEditarSeguimiento(@PathVariable Integer idSeguimiento, @PathVariable Integer idHallazgo, Model model) {
        log.info("Ejecutanto metodo [formularioEditarSeguimiento] idSeguimiento:{}, idHallazgo:{} ", idSeguimiento, idHallazgo);
        Seguimiento seguimiento = seguimientoService.buscarSeguimiento(idSeguimiento);
        model.addAttribute("idSeguimiento", seguimiento);
        model.addAttribute("idHallazgo", seguimiento.getOportunidadMejora().getIdHallazgo());
        model.addAttribute("seguimiento", seguimiento);
        return "comitePrograma\\proceso\\planMejoramiento\\seguimiento\\editar";
    }

}
