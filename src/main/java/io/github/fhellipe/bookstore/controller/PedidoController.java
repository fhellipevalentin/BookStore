package io.github.fhellipe.bookstore.controller;

import io.github.fhellipe.bookstore.model.Pedido;
import io.github.fhellipe.bookstore.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

}
