package io.github.fhellipe.bookstore.controller;

import io.github.fhellipe.bookstore.model.Livro;
import io.github.fhellipe.bookstore.model.LivroDTO;
import io.github.fhellipe.bookstore.model.Pedido;
import io.github.fhellipe.bookstore.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping("/{id}")
    public ResponseEntity<Livro> find(@PathVariable Integer id) {
        Livro obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<LivroDTO>> findPage(
            @RequestParam(value="titulo", defaultValue="") String titulo,
            @RequestParam(value="categorias", defaultValue="") String categorias,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="titulo") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        String tituloDecoded = URL.decodeParam(titulo);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Livro> list = service.search(tituloDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<LivroDTO> listDto = list.map(obj -> new LivroDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
