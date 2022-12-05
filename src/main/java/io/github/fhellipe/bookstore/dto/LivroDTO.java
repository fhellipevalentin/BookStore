package io.github.fhellipe.bookstore.dto;

import io.github.fhellipe.bookstore.model.Livro;

public class LivroDTO {

    private Integer id;
    private String titulo;
    private Double preco;
    private String autor;

    public LivroDTO(Livro obj) {
        id = obj.getId();
        titulo = obj.getTitulo();
        preco = obj.getPreco();
        autor = obj.getAutor();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
