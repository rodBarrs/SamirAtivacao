package com.samirAtivacao.DAO;

import com.samirAtivacao.modelo.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import  com.samirAtivacao.banco.ConexaoSQLite;

public class DAOUsuario extends ConexaoSQLite {
	public boolean salvarUsuarioDAO  (Usuario usuario) {
		conectar();
		String sql = "INSERT INTO usuario("
				+"nome, "
				+"senha,"
				+"cpf,"
				+ "etiqueta)"
				+"VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatement.setString(1, usuario.getNome());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getCpf());
			preparedStatement.setString(4, usuario.getEtiqueta());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desconectar();
		return true;
	}
	
	public boolean excluirUsuario() {
		conectar();
		PreparedStatement preparedStatment;
		String sql = "DELETE FROM usuario";
		preparedStatment = this.criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try {
			preparedStatment.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		} finally {
			if (preparedStatment != null) {
				try {
					preparedStatment.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		this.desconectar();
		return true;
	}
}
