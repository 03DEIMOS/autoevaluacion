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
import com.utb.autoevaluacion.service.TipoAccionService;
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
    
    @Autowired
    private TipoAccionService tipoAccionService;
    
    @GetMapping("/seguimientos/{idHallazgo}")
    public String oportunidadesMejora(@PathVariable Integer idHallazgo, Model model) {
        model.addAttribute("listaS", seguimientoService.getSeguimientoByOportunidadMejora(idHallazgo));
        return "comitePrograma\\proceso\\planMejoramiento\\seguimiento\\listar";

    }
    
    @GetMapping("/crear/{idHallazgo}")
    public String formularioCrearSeguimiento(@PathVariable Integer idHallazgo, Model model) {
        log.info("Ejecutanto método [formularioCrearSeguimiento] idHallazgo:{} ", idHallazgo);
        model.addAttribute("idHallazgo", idHallazgo);
        OportunidadMejora oportunidadMejora = oportunidadMejoraService.buscarOportunidadMejora(idHallazgo);
        model.addAttribute("tiposAccion", tipoAccionService.getTiposAccion());
        model.addAttribute("oportunidadMejora", oportunidadMejora);
        return "comitePrograma\\proceso\\planMejoramiento\\seguimiento\\crear";
    }
    
    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearSeguimiento(@RequestParam String fechaRealizado, 
            @RequestParam String avances, @RequestParam Integer estadoId, @RequestParam Integer idHallazgo) {

        log.info("Ejecutanto metodo [crearSeguimiento] fechaRealizado:{}, avances:{}, estadoId:{}, idHallazgo:{} ", fechaRealizado, avances, estadoId, idHallazgo);
        HttpStatus status;
        try {
            seguimientoService.crearSeguimiento(fechaRealizado, avances, idHallazgo);
            oportunidadMejoraService.actualizarEstadoOportunidadMejora(idHallazgo, estadoId);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " , e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }
    
    @GetMapping("/editar/{idSeguimiento}")
    public String formularioEditarSeguimiento(@PathVariable Integer idSeguimiento, Model model) {
        log.info("Ejecutanto metodo [formularioEditarSeguimiento] idSeguimiento:{} ", idSeguimiento);
        Seguimiento seguimiento = seguimientoService.buscarSeguimiento(idSeguimiento);
        model.addAttribute("seguimiento", seguimiento);
        OportunidadMejora oportunidadMejora = seguimiento.getOportunidadMejora();
        model.addAttribute("tiposAccion", tipoAccionService.getTiposAccion());
        model.addAttribute("oportunidadMejora", oportunidadMejora);
        return "comitePrograma\\proceso\\planMejoramiento\\seguimiento\\editar";
    }
    
    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarSeguimiento(@RequestParam Integer idSeguimiento, 
            @RequestParam String fechaRealizado, @RequestParam String avances, @RequestParam Integer estadoId) {
        log.info("Ejecutanto metodo [editarSeguimiento] idSeguimiento:{}, fechaRealizado:{}, avances:{}, estadoId:{} ",
                idSeguimiento, fechaRealizado, avances, estadoId);
        HttpStatus status;
        try {
            Seguimiento seguimiento = seguimientoService.buscarSeguimiento(idSeguimiento);
            seguimientoService.actualizarSeguimiento(idSeguimiento, fechaRealizado, avances);
            oportunidadMejoraService.actualizarEstadoOportunidadMejora(seguimiento.getOportunidadMejora().getIdHallazgo(), estadoId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " , e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }


}
