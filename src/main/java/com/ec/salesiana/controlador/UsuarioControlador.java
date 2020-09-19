package com.ec.salesiana.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ec.salesiana.modelo.Usuario;
import com.ec.salesiana.repositorio.UsuarioRepositorio;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class UsuarioControlador {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@GetMapping("/buscar/{usuario}")
	public Usuario buscarUsuario(@PathVariable("usuario") String usuario) {

		System.out.println("INICIANDO LA APP CONSULTA DE buscarUsuario");
		Usuario resultado = usuarioRepositorio.findByUsuario(usuario);

		return resultado;
	}

	@GetMapping("/listaUsuario")
	public List<Usuario> listaUsuario() {

		System.out.println("INICIANDO LA APP CONSULTA DE listaUsuario");
		List<Usuario> lResultado = new ArrayList<Usuario>();

		usuarioRepositorio.findAll().forEach(lResultado::add);

		return lResultado;
	}

	@PostMapping(value = "/guardar")
	public Usuario postCustomer(@RequestBody Usuario usuario) {

		Usuario _usuario = usuarioRepositorio.save(usuario);
		return _usuario;
	}

}
