package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.dto.UsuarioDTO;
import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.repositories.UsuarioRepository;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario find(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario insert(Usuario obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    public Usuario update(Usuario obj) {
        Usuario newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um cliente que possui entidades relacionadas a ele");
        }
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Usuario fromDTO(UsuarioDTO objDto) {
        return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Usuario newObj, Usuario obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
