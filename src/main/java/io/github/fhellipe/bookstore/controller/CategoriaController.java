package io.github.fhellipe.bookstore.controller;

import io.github.fhellipe.bookstore.model.Categoria;
import io.github.fhellipe.bookstore.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Categoria obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
