package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.model.Categoria;
import io.github.fhellipe.bookstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
