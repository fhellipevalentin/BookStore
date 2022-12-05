package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.model.Estado;
import io.github.fhellipe.bookstore.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository repo;

    public List<Estado> findAll() {
        return repo.findAllByOrderByNome();
    }
}
