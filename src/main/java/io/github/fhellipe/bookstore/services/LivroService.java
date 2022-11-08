package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.model.Categoria;
import io.github.fhellipe.bookstore.model.Livro;
import io.github.fhellipe.bookstore.repositories.CategoriaRepository;
import io.github.fhellipe.bookstore.repositories.LivroRepository;
import io.github.fhellipe.bookstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Livro find(Integer id) {
        Optional<Livro> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
    }

    public Page<Livro> search(String titulo, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(titulo, categorias, pageRequest);
    }
}
