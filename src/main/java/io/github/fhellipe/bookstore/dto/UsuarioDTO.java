package io.github.fhellipe.bookstore.dto;

import io.github.fhellipe.bookstore.model.Usuario;

public class UsuarioDTO {

    private Integer id;
    private String nome;
    private String email;

    public UsuarioDTO() {}

    public UsuarioDTO(Usuario obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
