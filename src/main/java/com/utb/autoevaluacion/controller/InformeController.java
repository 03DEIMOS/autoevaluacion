/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.service.InformeService;
import com.utb.autoevaluacion.service.ProcesoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Slf4j
@Controller
@RequestMapping("/informe")
public class InformeController {
    
    @Autowired
    InformeService informeService;
    
    @Autowired
    ProcesoService procesoService;
    
    @GetMapping("/estadoGeneralDelProceso/{procesoId}")
    public String estadoGeneralDelProceso(@PathVariable Integer procesoId, Model model) {
        Proceso proceso = null;
        List<Object> resultado = null;
        try {
            proceso = procesoService.buscarProceso(procesoId);
            resultado = informeService.estadoGeneralDelProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
        }
        model.addAttribute("resultado", resultado);
        model.addAttribute("proceso", proceso);
        return "comitePrograma\\proceso\\informe\\estadoProceso";
    }
    @GetMapping("/informeDMA/{procesoId}")
    public String informeDMAPorProceso(@PathVariable Integer procesoId, Model model) {

        List<Object> resultado = null;
        try {
            resultado = informeService.informeDMAPorProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
        }
        model.addAttribute("resultado", resultado);
        return "comitePrograma\\proceso\\informe\\dmaProgramas";
    }
}
