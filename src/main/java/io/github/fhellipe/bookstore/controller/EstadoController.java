package io.github.fhellipe.bookstore.controller;

import io.github.fhellipe.bookstore.dto.CidadeDTO;
import io.github.fhellipe.bookstore.dto.EstadoDTO;
import io.github.fhellipe.bookstore.model.Cidade;
import io.github.fhellipe.bookstore.model.Estado;
import io.github.fhellipe.bookstore.services.CidadeService;
import io.github.fhellipe.bookstore.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping(value="api/estados")
public class EstadoController {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> list = service.findAll();
        List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{estadoId}/cidades")
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
        List<Cidade> list = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
