/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.Usuario;
import java.util.List;




public interface UsuarioService {

	Usuario buscarUsuario(Integer id);
	
	Usuario buscarUsuarioPorUsuario(String usuario);
	
}
