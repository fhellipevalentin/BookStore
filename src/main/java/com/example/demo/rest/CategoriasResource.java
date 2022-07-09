package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriasResource {
	
	@Autowired
	private CategoriaRepository repo;

	@GetMapping("{id}")
	public Categoria findById( @PathVariable Integer id) {
		return repo
				.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@GetMapping
	public List<Categoria> findAll() {
		return repo.findAll();
	}
}
