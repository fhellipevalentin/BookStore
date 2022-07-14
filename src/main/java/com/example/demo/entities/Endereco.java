package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private String logradouro;
	@Column
	private String numero;
	@Column
	private String complemento;
	@Column
	private String bairro;
	@Column
	private String cep;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="cidade_id")
	private Cidade cidade;
	
	public Endereco() {
		
	}

	public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
			Usuario usuario, Cidade cidade) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.usuario = usuario;
		this.setCidade(cidade);
	}
}
