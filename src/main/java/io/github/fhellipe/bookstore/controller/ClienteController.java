package io.github.fhellipe.bookstore.controller;

import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/usuarios")
public class ClienteController {

    @Autowired
    private UsuarioService service;

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Usuario obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }
}
