package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.dto.CategoriaDTO;
import io.github.fhellipe.bookstore.model.Cidade;
import io.github.fhellipe.bookstore.repositories.CategoriaRepository;
import io.github.fhellipe.bookstore.repositories.CidadeRepository;
import io.github.fhellipe.bookstore.services.exceptions.DataIntegrityException;
import io.github.fhellipe.bookstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findByEstado(Integer estadoId) {
        return repo.findCidades(estadoId);
    }
}
