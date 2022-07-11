package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Livro;
import com.example.demo.entities.repository.CategoriaRepository;
import com.example.demo.entities.repository.LivroRepository;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
	
	@Override
	public void run (String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Comércio");
		Categoria cat2 = new Categoria(null, "Jurídicos");
		
		Livro l1 = new Livro(null, "Eu, Juridico", "Jurencio", 80.00);
		Livro l2 = new Livro(null, "As crônicas do Comércio", "Comerciante", 24.00);
		Livro l3 = new Livro(null, "Suits, uma licao para a Vida", "Maguire Goodman", 56.00);
		
		l1.getCategorias().addAll(Arrays.asList(cat2));
		l2.getCategorias().addAll(Arrays.asList(cat1));
		l3.getCategorias().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		livroRepository.saveAll(Arrays.asList(l1, l2, l3));
	}
}
