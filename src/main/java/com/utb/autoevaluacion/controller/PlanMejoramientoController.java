/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.PlanMejoramiento;
import com.utb.autoevaluacion.model.Programa;
import com.utb.autoevaluacion.model.Usuario;
import com.utb.autoevaluacion.service.PlanMejoramientoService;
import com.utb.autoevaluacion.service.ProgramaService;
import com.utb.autoevaluacion.service.UsuarioService;
import java.time.LocalDate;
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
@RequestMapping("/planMejoramiento")
public class PlanMejoramientoController {

    @Autowired
    PlanMejoramientoService planMejoramientoService;

    @Autowired
    ProgramaService programaService;
    
    @Autowired 
    UsuarioService usuarioService;

    @GetMapping("/listar/{usuarioId}")
    public String listar(@PathVariable Integer usuarioId, Model model) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        if("Administrador".equals(usuario.getTipo())){
            model.addAttribute("listPlanes", planMejoramientoService.buscarPlanesMejoramiento());
        }else{
           model.addAttribute("listPlanes", planMejoramientoService.buscarPlanesMejoramientoByUserId(usuarioId));
        }
        model.addAttribute("usuarioId", usuarioId);
        return "comiteCentral\\planesMejoramiento\\listar";
    }

    @GetMapping("/crear/{usuarioId}")
    public String formularioCrearPlanMejoramiento(@PathVariable Integer usuarioId, Model model) {
        log.info("Ejecutanto metodo [formularioCrearPlanMejoramiento]");
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        if("Administrador".equals(usuario.getTipo())){
            model.addAttribute("programas", programaService.getProgramas());
        }else{
           model.addAttribute("programas", usuario.getProgramaList());
        }
        model.addAttribute("usuarioId", usuarioId);
        return "comiteCentral\\planesMejoramiento\\crear";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearProceso(@RequestParam Integer programaId, @RequestParam String plan) {

        log.info("Ejecutanto metodo crearProceso programaId:{}, plan:{} ", programaId, plan);
        HttpStatus status;
        try {
            PlanMejoramiento planMejoramiento = new PlanMejoramiento();
            planMejoramiento.setPlan(plan);
            Programa programa = programaService.buscarPrograma(programaId);
            planMejoramiento.setProgramaId(programa);
            planMejoramiento.setFechaInicio(LocalDate.now() + "");
            planMejoramiento.setEstado("Activo");
            planMejoramientoService.crearPlanMejoramiento(planMejoramiento);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @GetMapping("/editar/{planMejoramientoId}/{usuarioId}")
    public String formularioEditarPlanMejoramiento(@PathVariable Integer planMejoramientoId, @PathVariable Integer usuarioId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarPlanMejoramiento] planMejoramientoId:{} ", planMejoramientoId);
        PlanMejoramiento planMejoramiento = planMejoramientoService.buscarPlanMejoramiento(planMejoramientoId);
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        if("Administrador".equals(usuario.getTipo())){
           model.addAttribute("programas", programaService.getProgramas());
        }else{
          model.addAttribute("programas", usuario.getProgramaList());
        }
        model.addAttribute("planMejoramiento", planMejoramiento);
        model.addAttribute("usuarioId", usuarioId);
        return "comiteCentral\\planesMejoramiento\\editar";
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarPlanMejoramiento(@RequestParam Integer planMejoramientoId, @RequestParam String plan, @RequestParam Integer programaId,
            @RequestParam String estado) {

        log.info("Ejecutanto metodo [editarPlanMejoramiento] planMejoramientoId:{}, plan:{}, programaId:{}, estado:{} ", planMejoramientoId, plan, programaId, estado);
        HttpStatus status;
        try {
            PlanMejoramiento planMejoramiento = planMejoramientoService.buscarPlanMejoramiento(planMejoramientoId);
            planMejoramiento.setPlan(plan);
            Programa programa = programaService.buscarPrograma(programaId);
            planMejoramiento.setProgramaId(programa);
            planMejoramiento.setEstado(estado);
            if (estado.equals("Finalizado")) {
                planMejoramiento.setFechaCierre(LocalDate.now() + "");
            }
            planMejoramientoService.actualizarPlanMejoramiento(planMejoramiento);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);

    }

}
