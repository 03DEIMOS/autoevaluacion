/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.model.Usuario;
import com.utb.autoevaluacion.repository.FuenteRepository;
import com.utb.autoevaluacion.repository.PersonaRepository;
import com.utb.autoevaluacion.repository.ProcesoRepository;
import com.utb.autoevaluacion.repository.UsuarioRepository;
import com.utb.autoevaluacion.service.PersonaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProcesoRepository procesoRepository;
    @Autowired
    FuenteRepository fuenteRepository;

    @Override
    public List<Persona> buscarPersonaPorUsuarioActivaYEsMuestra(String identificacion) {
        List<Persona> personas = null;
        Usuario usuario = usuarioRepository.findByUsuario(identificacion);

        try {
            if (usuario != null) {
                personas = personaRepository.buscarPersonaPorUsuarioIdActivaYEsMuestra(usuario.getId());
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error buscando al usuario :{}", e);
        }
        return personas;
    }

    @Override
    public Persona buscarPersona(Integer personaId) {
        Persona persona = null;
        Optional<Persona> personaOptional = null;
        try {
            personaOptional = personaRepository.findById(personaId);
            if (personaOptional.isPresent()) {
                persona = personaOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return persona;
    }

    @Override
    public List<Persona> buscarPoblacionPorProcesoYFuenteActivaYEsMuestra(Integer procesoId, Integer fuenteId) {
        List<Persona> personas = null;
        try {
            personas = personaRepository.buscarPoblacionPorProcesoYFuenteActivaYEsMuestra(procesoId, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return personas;
    }

    @Override
    public List<Persona> muestraPorProceso(Integer procesoId) {

        List<Persona> personas = null;
        try {
            personas = personaRepository.muestraPorProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return personas;
    }

    @Override
    public List<Persona> personasEncuestaTerminadaPorProceso(Integer procesoId) {
        List<Persona> personas = null;
        try {
            personas = personaRepository.muestraPorProcesoEncuestaTerminada(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return personas;
    }

    @Override
    public Integer cantidadMuestraEncuestaTerminadaPorProcesoFuente(Integer procesoId, Integer fuenteId) {
        Integer cantidadMuestraEncuestaTerminadaPorProcesoFuente = 0;
        try {
            cantidadMuestraEncuestaTerminadaPorProcesoFuente = personaRepository.cantidadMuestraEncuestaTerminadaPorProcesoFuente(procesoId, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return cantidadMuestraEncuestaTerminadaPorProcesoFuente;
    }

    @Override
    public Integer cantidadTotalMuestraPorProcesoFuente(Integer procesoId, Integer fuenteId) {
        Integer cantidadTotalMuestraPorProcesoFuente = 0;
        try {
            cantidadTotalMuestraPorProcesoFuente = personaRepository.cantidadTotalMuestraPorProcesoFuente(procesoId, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return cantidadTotalMuestraPorProcesoFuente;
    }

    @Override
    public List buscarVariablesPorProcesoYFuenteActivaYTerminado(Integer procesoId, Integer fuenteId, String variable) {
        List variables1 = null;
        try {
            if (variable.equals("variable1")) {
                variables1 = personaRepository.buscarVariables1PorProcesoYFuenteActivaYTerminada(procesoId, fuenteId);
            } else {
                variables1 = personaRepository.buscarVariables2PorProcesoYFuenteActivaYTerminada(procesoId, fuenteId);
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return variables1;
    }

    @Override
    public void crearEvaluador(String identificacion, String codigo, String nombre, String apellido, String correo, String variable1, String variable2, Integer fuenteId, Integer procesoId) {
        Proceso proceso = procesoRepository.findById(procesoId).get();
        Fuente fuente = fuenteRepository.findById(fuenteId).get();

        Persona persona = new Persona(variable1, variable2, proceso, fuente, "A", "N", "S",
                new Usuario(codigo, identificacion, nombre, apellido, correo, "A", true));

        Usuario usuarioBd = usuarioRepository.findByUsuario(persona.getUsuarioId().getUsuario());
        if (usuarioBd == null) {
            Usuario idUsuario = usuarioRepository.saveAndFlush(persona.getUsuarioId());
            persona.setUsuarioId(idUsuario);
            try {
                personaRepository.save(persona);
            } catch (Exception e) {
                log.error("Ha ocurrido un error creando a la persona error:{} ", e);
            }

        } else {
            log.info("Usuario ya existe y no se creara usuario.codigo :{}", usuarioBd.getUsuario());
            Persona personaAux = personaRepository.buscarPersonaPorProcesoFuenteYUsuarioActiva(procesoId, fuenteId, usuarioBd.getId());
            if (personaAux == null) {
                persona.setUsuarioId(usuarioBd);
                personaRepository.save(persona);
            }
        }
    }

    @Override
    public void actualizarEvaluador(Integer personaId, String identificacion, String codigo, String nombre, String apellido, String correo, String variable1, String variable2, Integer fuenteId, Integer procesoId) {
        Persona persona = personaRepository.findById(personaId).get();
        persona.setVariable1(variable1);
        persona.setVariable2(variable2);
        persona.getUsuarioId().setIdentificacion(identificacion);
        persona.getUsuarioId().setNombre(nombre);
        persona.getUsuarioId().setApellido(apellido);
        persona.getUsuarioId().setEmail(correo);
        personaRepository.save(persona);       

    }
}
