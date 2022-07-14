package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Usuario;
import com.example.demo.entities.repository.UsuarioRepository;

@RestController
@RequestMapping(value="api/usuarios")
public class ClienteResource {
	
	@Autowired
	private UsuarioRepository repo;
	
	@GetMapping("{id}")
	public Usuario findbyId(@PathVariable Integer id) {
		return repo
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
	}
}
