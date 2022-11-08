package io.github.fhellipe.bookstore.repositories;

import io.github.fhellipe.bookstore.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Transactional(readOnly=true)
    Usuario findByEmail(String email);
}
