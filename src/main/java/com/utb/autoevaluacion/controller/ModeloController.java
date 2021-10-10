
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping("/crear" )
    public String crearModelo(@ModelAttribute("modelo") Modelo modelo, Model model) {
        Modelo modeloGuardado = modeloService.createModelo(modelo);
        //model.addAttribute("modelo", modeloService.createModelo(modelo));
        return "comiteCentral\\modelo\\crear";
        
    } 
    
    @PostMapping("/editar/{id}" )
    public String editarModelo(@PathVariable Integer id,Model model) {
        //model.addAttribute("modelo", modeloService.createModelo(modelo));
        return "comiteCentral\\modelo\\listar";
        
    } 
}
