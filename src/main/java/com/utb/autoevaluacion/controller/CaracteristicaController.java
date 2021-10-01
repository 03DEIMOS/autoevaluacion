package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.service.CaracteristicaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/caracteristica")
public class CaracteristicaController {
    
    private final CaracteristicaService caracteristicaService;

    public CaracteristicaController(CaracteristicaService caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    @GetMapping("/caracteristicas")
    public String caracteristicas(Model model) {
        model.addAttribute("listaC", caracteristicaService.getCaracteristica());
        return "comiteCentral\\caracteristica\\listar";
        
    } 
    
}
