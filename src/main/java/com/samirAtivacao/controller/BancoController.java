package com.samirAtivacao.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.samirAtivacao.banco.ConexaoSQLite;
import com.samirAtivacao.modelo.Usuario;

public class BancoController {
	ControllerUsuario controller;
	 public Usuario loginInformacoes() {
		 Usuario usuario =  new Usuario();
		 ConexaoSQLite conexao = new ConexaoSQLite();
	        conexao.conectar();
	        String sql = "SELECT * FROM usuario where id= 1";
	        PreparedStatement stmt = conexao.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        try {
	        	ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	usuario.setNome(rs.getString("nome"));
	            	usuario.setCpf(rs.getString("cpf"));
	            	usuario.setSenha(rs.getString("senha"));
	            	usuario.setEtiqueta(rs.getString("etiqueta"));
	            	
	            }
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	        conexao.desconectar();
	        return usuario;
		 
	 }

	
}
