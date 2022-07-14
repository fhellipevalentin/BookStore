package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Cidade;
import com.example.demo.entities.Estado;
import com.example.demo.entities.Livro;
import com.example.demo.entities.repository.CategoriaRepository;
import com.example.demo.entities.repository.CidadeRepository;
import com.example.demo.entities.repository.EstadoRepository;
import com.example.demo.entities.repository.LivroRepository;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
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
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Rio De Janeiro");
		Estado est3 = new Estado(null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est3);
		Cidade c2 = new Cidade(null, "Rio de Janeiro", est2);
		Cidade c3 = new Cidade(null, "Campinas", est1);
		Cidade c4 = new Cidade(null, "São Paulo", est1);
		
		est1.getCidades().addAll(Arrays.asList(c3,c4));
		est2.getCidades().addAll(Arrays.asList(c2));
		est3.getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
	}
}
