package com.samirAtivacao.controller;

import com.samirAtivacao.modelo.Usuario;

public class BancoController {
	ControllerUsuario controller;
	 public String salvar(Usuario usuario) {
		 boolean teste = controller.salvarUsuarioController(usuario);
		 if(teste == true) {
			 return "funcionou";
		 }
		 else {
			 return "vai tomar no cu";
		 }
		 
	 }
	
}
