/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.auth.DbAuthProvider;
import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Usuario;
import com.utb.autoevaluacion.model.Variable;
import com.utb.autoevaluacion.service.EncuestaService;
import com.utb.autoevaluacion.service.PersonaService;
import com.utb.autoevaluacion.service.UsuarioService;
import com.utb.autoevaluacion.service.VariableService;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.provider.provisioning.SamlProviderProvisioning;
import org.springframework.security.saml.provider.service.ServiceProviderService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    PersonaService personaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EncuestaService encuestaService;

    @Autowired
    VariableService variableService;

    @Autowired
    DbAuthProvider dbAuthProvider;

    private SamlProviderProvisioning<ServiceProviderService> provisioning;

    @Value("${saml.discovery.url:autoevaluacion/saml/sp/discovery}")
    private String samlDiscoveryUrl;

    @Value("${saml.discovery.entity-id:https://sts.windows.net/ea649e50-27d2-4318-9cb9-2fcffe16fd41/}")
    private String samlDiscoveryEntityId;

    @Autowired
    public void setSamlService(SamlProviderProvisioning<ServiceProviderService> provisioning) {
        this.provisioning = provisioning;
    }

    @GetMapping("/admin")
    public String indexAdmin() {
        return "indexAdmin";
    }

    @PostMapping("cerrarSesion")
    public String cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        log.info("Ejecutando metodo cerrarSesion");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "indexAdmin";
    }

    @RequestMapping(value = {"/public"})
    public String publico(Model model) {
        model.addAttribute("samlLink", "0; url='" + samlDiscoveryUrl + "?idp=" + samlDiscoveryEntityId + "'");
        return "fuente\\index";
    }

    @RequestMapping(value = {"/"})
    public String home(Model model) {
        //Obtengo el codigo T000 de la persona
        String codigo = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println("Codigo : " + codigo);
        System.out.println();

        log.info("Ejecutando metodo validar codigo:{}", codigo);
        List<Persona> personas = personaService.buscarPersonaPorUsuarioActivaYEsMuestra(codigo);
        if (personas != null && !personas.isEmpty()) {
            for (Persona persona : personas) {
                if (persona.getTerminado().equals("N")) {
                    Encuesta encuesta = encuestaService.obtenerEncuestasDePersona(persona);
                    model.addAttribute("encuesta", encuesta);
                    model.addAttribute("persona", persona);
                    return "fuente\\index";
                }
            }
            log.info("Devuelto al index");
            model.addAttribute("errorLogin", true);
            model.addAttribute("message", "Usuario ya realizó la encuesta");
            return "indexAdmin";
        } else {
            log.info("Devuelto al index");
            model.addAttribute("errorLogin", true);
            model.addAttribute("message", "Código de usuario no registrado");
            return "indexAdmin";
        }

    }

    /*
    @PostMapping("/")
    public String validar(@RequestParam String codigo, Model model) {
        log.info("Ejecutando metodo validar codigo:{}", codigo);
        List<Persona> personas = personaService.buscarPersonaPorUsuarioActivaYEsMuestra(codigo);
        if (personas != null && !personas.isEmpty()) {
            for (Persona persona : personas) {
                if (persona.getTerminado().equals("N")) {
                    Encuesta encuesta = encuestaService.obtenerEncuestasDePersona(persona);
                    model.addAttribute("encuesta", encuesta);
                    model.addAttribute("persona", persona);
                    return "fuente\\index";
                }
            }
            log.info("Devuelto al index");
            model.addAttribute("errorLogin", true);
            model.addAttribute("message", "Usuario ya realizó la encuesta");
            return "index";
        } else {
            log.info("Devuelto al index");
            model.addAttribute("errorLogin", true);
            model.addAttribute("message", "Código de usuario no registrado");
            return "index";
        }
    }
     */
    @PostMapping("/admin")
    public String validarAdmin(@RequestParam String codigo, @RequestParam String password, Model model) {
        log.info("Ejecutando metodo validarAdmin codigo:{}", codigo);

        try {
            Authentication authentication = dbAuthProvider.authenticate(new UsernamePasswordAuthenticationToken(codigo, password));
            if (authentication.isAuthenticated()) {
                Usuario usuario = usuarioService.buscarUsuarioPorUsuario(codigo);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                model.addAttribute("usuario", usuario);
                String textoIndex = "";
                Variable variableTextoIndex = variableService.getVariableByLlave("CONTENIDO_PANTALLA_INICIAL");
                if (variableTextoIndex != null) {
                    textoIndex = variableTextoIndex.getValor();
                }
                model.addAttribute("textoIndex", textoIndex);
                if ("Administrador".equals(usuario.getTipo())) {
                    return "comiteCentral\\index";
                } else {
                    return "comitePrograma\\index";
                }
            }
        } catch (AuthenticationException e) {
            log.error("Login incorrecto: ", e);
            model.addAttribute("errorLogin", true);
            model.addAttribute("message", "Usuario/Contraseña no válidos");
            return "indexAdmin";
        }
        return "indexAdmin";
    }
}
