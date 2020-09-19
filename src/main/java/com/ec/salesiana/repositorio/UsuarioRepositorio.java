package com.ec.salesiana.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.ec.salesiana.modelo.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

	public Usuario findByUsuario(String usuario);
	
}
