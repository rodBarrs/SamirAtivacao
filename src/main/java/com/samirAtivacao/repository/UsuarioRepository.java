package com.samirAtivacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samirAtivacao.modelo.UsuarioModelo;



@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModelo, Long> {
}