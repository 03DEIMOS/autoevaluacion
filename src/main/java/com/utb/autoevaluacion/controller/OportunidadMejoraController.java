package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.model.PlanMejoramiento;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.service.CaracteristicaService;
import com.utb.autoevaluacion.service.OportunidadMejoraService;
import com.utb.autoevaluacion.service.PlanMejoramientoService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private PlanMejoramientoService planMejoramientoService;

    @Autowired
    private CaracteristicaService caracteristicaService;

    @Autowired
    private OportunidadMejoraService oportunidadMejoraService;

    @GetMapping("/oportunidadesMejora/{planMejoramientoId}")
    public String oportunidadesMejora(@PathVariable Integer planMejoramientoId, Model model) {
        model.addAttribute("listaO", oportunidadMejoraService.getOportunidadMejoraByPlanMejoramiento(planMejoramientoId));
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\listar";

    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOportunidadMejora(@RequestParam Integer oportunidadMejoramientoId) {

        log.info("Ejecutanto metodo [eliminarOportunidadMejora] oportunidadMejoramientoId:{}", oportunidadMejoramientoId);
        HttpStatus status;
        try {
            oportunidadMejoraService.eliminarOportunidadMejora(oportunidadMejoramientoId);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @GetMapping("/crear/{planMejoramientoId}")
    public String formularioCrearOportunidadMejora(@PathVariable Integer planMejoramientoId, Model model) {
        log.info("Ejecutanto método [formularioCrearOportunidadMejora] planMejoramientoId:{} ", planMejoramientoId);
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        model.addAttribute("listaC", caracteristicaService.getCaracteristicas());
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\crear";
    }

    @GetMapping("/clonar/{planMejoramientoId}")
    public String formularioClonarOportunidadMejora(@PathVariable Integer planMejoramientoId, Model model) {
        log.info("Ejecutanto método [formularioClonarOportunidadMejora] planMejoramientoId:{} ", planMejoramientoId);
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        model.addAttribute("planesMejoramiento", planMejoramientoService.buscarPlanesMejoramiento());
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\clonar";
    }

    @GetMapping("/editar/{idHallazgo}")
    public String formularioEditarOportunidadMejora(@PathVariable Integer idHallazgo, Model model) {
        log.info("Ejecutanto metodo [formularioEditarOportunidadMejora] idHallazgo:{} ", idHallazgo);
        OportunidadMejora oportunidadMejora = oportunidadMejoraService.buscarOportunidadMejora(idHallazgo);
        model.addAttribute("listaC", caracteristicaService.getCaracteristicas());
        model.addAttribute("planMejoramientoId", oportunidadMejora.getPlanMejoramientoId().getId());
        model.addAttribute("oportunidadMejora", oportunidadMejora);
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\editar";
    }
    
    @GetMapping("/reporteCuantitativo/{planMejoramientoId}")
    public String reporteCuantitativo(@PathVariable Integer planMejoramientoId, Model model) {
        log.info("Ejecutanto metodo [reporteCuantitativo] planMejoramientoId:{} ", planMejoramientoId);
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        model.addAttribute("abierta", oportunidadMejoraService.getOportunidadMejoraByPlanMejoramientoAndStatus(planMejoramientoId, "Abierta").size());
        model.addAttribute("cerrada", oportunidadMejoraService.getOportunidadMejoraByPlanMejoramientoAndStatus(planMejoramientoId, "Cerrada").size());
        model.addAttribute("permanente", oportunidadMejoraService.getOportunidadMejoraByPlanMejoramientoAndStatus(planMejoramientoId, "Permanente").size());
        return "comitePrograma\\proceso\\informe\\reporteCuantitativo";
    }
    

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearOportunidadMejora(@RequestParam String oportunidadMejoramiento, @RequestParam Integer planMejoramientoId,
            @RequestParam Integer caracteristicaId, @RequestParam String eje, @RequestParam String lineaAccion, @RequestParam String estado,
            @RequestParam String tipo, @RequestParam String responsable, @RequestParam String fechaInicio, @RequestParam String fechaFinal) {

        log.info("Ejecutanto metodo [crearOportunidadMejora] "
                + "oportunidadMejoramiento:{}, planMejoramientoId:{}, caracteristicaId:{}, ejeEstrategico:{}, lineaAccion:{}, estado:{}, tipo:{}, responsable:{}, fechaInicio:{}, fechaFinal:{}",
                oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, estado, tipo, responsable, fechaInicio, fechaFinal);
        HttpStatus status;
        try {

            oportunidadMejoraService.crearOportunidadMejora(oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, estado, tipo, responsable, fechaInicio, fechaFinal);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @PostMapping(value = "/clonar")
    public ResponseEntity<?> clonarOportunidadesMejora(@RequestParam Integer planMejoramientoOrigen, @RequestParam Integer planMejoramientoDestino) {
        log.info("Ejecutanto metodo [clonarOportunidadesMejora] "
                + "planMejoramientoOrigen:{}, planMejoramientoDestino:{}", planMejoramientoOrigen, planMejoramientoDestino);
        HttpStatus status;
        try {
            List<OportunidadMejora> oportunidadMejoraOrigen = oportunidadMejoraService.getOportunidadMejoraByPlanMejoramiento(planMejoramientoOrigen);
            for (OportunidadMejora oportunidadMejora : oportunidadMejoraOrigen) {
                oportunidadMejoraService.crearOportunidadMejora(
                        oportunidadMejora.getHallazgo(),
                        planMejoramientoDestino,
                        oportunidadMejora.getCaracteristicaId().getId(),
                        oportunidadMejora.getEje(),
                        oportunidadMejora.getLineaAccion(),
                        oportunidadMejora.getEstado(),
                        oportunidadMejora.getTipo(),
                        oportunidadMejora.getResponsable(),
                        oportunidadMejora.getFechaInicio(),
                        oportunidadMejora.getFechaFin());
            }
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarOportunidadMejora(@RequestParam Integer hallazgoId, @RequestParam String oportunidadMejoramiento, @RequestParam Integer planMejoramientoId,
            @RequestParam Integer caracteristicaId, @RequestParam String eje, @RequestParam String lineaAccion, @RequestParam String estado, @RequestParam String tipo,
            @RequestParam String responsable, @RequestParam String fechaInicio, @RequestParam String fechaFinal) {
        log.info("Ejecutanto metodo [editarOportunidadMejora] "
                + "hallazgoId:{}, oportunidadMejoramiento:{}, planMejoramientoId:{}, caracteristicaId:{}, eje:{}, lineaAccion:{}, estado:{}, tipo:{}, responsable:{}, fechaInicio:{}, fechaFinal:{} ",
                hallazgoId, oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, estado, tipo, responsable, fechaInicio, fechaFinal);
        HttpStatus status;
        try {
            oportunidadMejoraService.actualizarOportunidadMejora(hallazgoId, oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, estado, tipo, responsable, fechaInicio, fechaFinal);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

}
