package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String titulo;
	@Column
	private String autor;
	@Column
	private Double preco;
	
	@ManyToMany
	@JoinTable(name = "LIVRO_CATEGORIA",
			joinColumns = @JoinColumn(name = "livro_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

	public Livro(Integer id, String titulo, String autor, Double preco) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.preco = preco;
	}	
	
}
