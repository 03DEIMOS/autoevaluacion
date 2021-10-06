
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modelo")
public class ModeloController {
   
   @Autowired 
   private ModeloService modeloService;

    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping("/modelos")
    public String modelos(Model model) {
        model.addAttribute("listaM", modeloService.getModelos());
        return "comiteCentral\\modelo\\listar";
        
    } 
    
     @GetMapping("/entrar/{id}" )
    public String entrarModelo(@PathVariable Integer id, Model model) {
        model.addAttribute("modelo", modeloService.getModeloById(id));
        return "comiteCentral\\inicio";
        
    } 
}
