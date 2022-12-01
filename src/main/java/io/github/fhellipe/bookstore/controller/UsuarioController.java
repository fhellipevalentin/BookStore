package io.github.fhellipe.bookstore.controller;

import io.github.fhellipe.bookstore.dto.CategoriaDTO;
import io.github.fhellipe.bookstore.dto.UsuarioDTO;
import io.github.fhellipe.bookstore.dto.UsuarioNewDTO;
import io.github.fhellipe.bookstore.model.Categoria;
import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.services.UsuarioService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("{id}")
    public ResponseEntity<Usuario> find(@PathVariable Integer id) {
        Usuario obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDTO objDto) {
        Usuario obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody UsuarioDTO objDTO, @PathVariable Integer id) {
        Usuario obj = service.fromDTO(objDTO);
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> list = service.findAll();
        List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<Page<UsuarioDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Usuario> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<UsuarioDTO> listDto = list.map(obj -> new UsuarioDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("{id}/foto")
    public byte[ ] addPhoto( @PathVariable Integer id, @RequestParam("foto") Part arquivo) {
        Optional<Usuario> contato = Optional.ofNullable(service.find(id));
        return contato.map( c -> {
            try {
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully( is, bytes );
                c.setFoto(bytes);
                is.close();
                return bytes;
            }
            catch (IOException e) {
                return null;
            }
        }).orElse(null);
    }
}
