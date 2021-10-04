/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    
    @PostMapping("/validate")
    public String validar(@RequestParam String codigo, @RequestParam String clave ) {
         System.out.println("Logueando a user:"+codigo+" pw:"+clave);
        return "comiteCentral\\index";
    } 
}
