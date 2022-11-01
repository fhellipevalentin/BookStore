package io.github.fhellipe.bookstore.repositories;

import io.github.fhellipe.bookstore.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
