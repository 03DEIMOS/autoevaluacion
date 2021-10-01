
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.service.FactorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/factor")
public class FactorController {
    
    private final FactorService factorService;

    public FactorController(FactorService factorService) {
        this.factorService = factorService;
    }

    @GetMapping("/factores")
    public String factores(Model model) {
        model.addAttribute("listaF", factorService.getFactores());
        return "comiteCentral\\factor\\listar";
        
    } 
}
