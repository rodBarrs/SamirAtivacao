package com.samirAtivacao.controller;

import com.samirAtivacao.DAO.DAOUsuario;
import com.samirAtivacao.modelo.Usuario;


public class ControllerUsuario {

	DAOUsuario DAOUsuario = new DAOUsuario();

	public boolean salvarUsuarioController(Usuario usuario) {
		
		return this.DAOUsuario.salvarUsuarioDAO(usuario);
		
	}
	
	public boolean excluirUsuarioController () {
		return this.DAOUsuario.excluirUsuario();
	}
}
