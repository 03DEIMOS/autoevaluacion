package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.model.Proceso;
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
import org.springframework.web.bind.annotation.PutMapping;
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
        Proceso proceso = procesoService.buscarProceso(procesoId);
        model.addAttribute("procesoId", procesoId);
        model.addAttribute("listaC", caracteristicaService.getCaracteristicasByModelo(proceso.getModeloId().getId()));
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\crear";
    }
    
    
    @GetMapping("/editar/{idHallazgo}")
    public String formularioEditarOportunidadMejora(@PathVariable Integer idHallazgo, Model model) {
        log.info("Ejecutanto metodo [formularioEditarOportunidadMejora] idHallazgo:{} ", idHallazgo);
        OportunidadMejora oportunidadMejora = oportunidadMejoraService.buscarOportunidadMejora(idHallazgo);
        model.addAttribute("listaC", caracteristicaService.getCaracteristicasByModelo(oportunidadMejora.getProcesoId().getModeloId().getId()));
        model.addAttribute("procesoId", oportunidadMejora.getProcesoId().getId());
        model.addAttribute("oportunidadMejora", oportunidadMejora);
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\editar";
    }
    

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearOportunidadMejora(@RequestParam String oportunidadMejoramiento, @RequestParam Integer procesoId, 
            @RequestParam Integer caracteristicaId, @RequestParam String eje, @RequestParam String lineaAccion, @RequestParam String estado,
            @RequestParam String responsable, @RequestParam String fechaInicio, @RequestParam String fechaFinal) {

        log.info("Ejecutanto metodo [crearOportunidadMejora] caracteristicaId:{}, nombre:{}, ejeEstrategico:{}, lineaAccion:{}, estado:{}, responsable:{} ", oportunidadMejoramiento, procesoId, caracteristicaId, eje, lineaAccion, estado, responsable);
        HttpStatus status;
        try {
            
            oportunidadMejoraService.crearOportunidadMejora(oportunidadMejoramiento, procesoId, caracteristicaId, eje, lineaAccion, estado, responsable, fechaInicio, fechaFinal);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " , e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }
    
    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarOportunidadMejora(@RequestParam Integer hallazgoId, @RequestParam String oportunidadMejoramiento, @RequestParam Integer procesoId, 
            @RequestParam Integer caracteristicaId, @RequestParam String eje, @RequestParam String lineaAccion, @RequestParam String estado,
            @RequestParam String responsable, @RequestParam String fechaInicio, @RequestParam String fechaFinal) {
        log.info("Ejecutanto metodo [editarOportunidadMejora] hallazgoId:{}, oportunidadMejoramiento:{}, procesoId:{}, caracteristicaId:{} "
                + " eje:{}, lineaAccion:{}, estado:{}, responsable:{}, fechaInicio:{}, fechaFinal:{} ",
                hallazgoId, oportunidadMejoramiento, procesoId, caracteristicaId, eje, lineaAccion, estado, responsable, fechaInicio, fechaFinal);
        HttpStatus status;
        try {
            oportunidadMejoraService.actualizarOportunidadMejora(hallazgoId, oportunidadMejoramiento, procesoId, caracteristicaId, eje, lineaAccion, estado, responsable, fechaInicio, fechaFinal);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " , e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

}
