/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Usuario;
import java.util.List;




public interface UsuarioService {
        
        void crearUsuario(String codigo, String identificacion, String nombre, String apellidos, String clave, String email, Integer[] programas);
        
	Usuario buscarUsuario(Integer id);
	
	Usuario buscarUsuarioPorUsuario(String usuario);
        
        List<Usuario> buscarUsuarios();

        Usuario buscarUsuarioPorId(Integer usuarioId);

        void actualizarUsuario(Integer usuarioId, String codigo, String identificacion, String nombre, String apellidos, String email, Integer[] programas);

        void cambiarContrasena(Integer usuarioId, String claveActual, String nuevaClave1, String nuevaClave2) throws Exception;
	
}
