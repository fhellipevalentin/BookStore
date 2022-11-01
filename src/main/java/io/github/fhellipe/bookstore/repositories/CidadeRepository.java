package io.github.fhellipe.bookstore.repositories;

import io.github.fhellipe.bookstore.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer> {
}
