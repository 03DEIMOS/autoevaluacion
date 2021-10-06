
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.service.FactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/factor")
public class FactorController {
    @Autowired
    private FactorService factorService;

    public FactorController(FactorService factorService) {
        this.factorService = factorService;
    }

    @GetMapping("/factores/{modeloId}")
    public String factores(@PathVariable Integer modeloId, Model model) {
        model.addAttribute("listaF", factorService.getFactoresByModelo(modeloId));
        return "comiteCentral\\factor\\listar";
        
    } 
}
