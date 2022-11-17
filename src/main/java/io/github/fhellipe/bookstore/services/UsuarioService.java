package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.dto.UsuarioDTO;
import io.github.fhellipe.bookstore.dto.UsuarioNewDTO;
import io.github.fhellipe.bookstore.enums.Perfil;
import io.github.fhellipe.bookstore.enums.TipoUsuario;
import io.github.fhellipe.bookstore.model.Cidade;
import io.github.fhellipe.bookstore.model.Endereco;
import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.model.Usuario;
import io.github.fhellipe.bookstore.repositories.EnderecoRepository;
import io.github.fhellipe.bookstore.repositories.UsuarioRepository;
import io.github.fhellipe.bookstore.security.UserSS;
import io.github.fhellipe.bookstore.services.exceptions.AuthorizationException;
import io.github.fhellipe.bookstore.services.exceptions.DataIntegrityException;
import io.github.fhellipe.bookstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Usuario find(Integer id) {

        UserSS user = UserService.authenticated();
        if ( user == null || user.hashRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso Negado!");
        }

        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    @Transactional
    public Usuario insert(Usuario obj) {
        obj.setId(null);
        obj = repository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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
            throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos relacionados a ele");
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
        return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    public Usuario fromDTO(UsuarioNewDTO objDto) {
        Usuario cli = new Usuario(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoUsuario.toEnum(objDto.getTipo()), passwordEncoder.encode(objDto.getSenha()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    private void updateData(Usuario newObj, Usuario obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
