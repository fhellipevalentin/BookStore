package io.github.fhellipe.bookstore.repositories;

import io.github.fhellipe.bookstore.model.Categoria;
import io.github.fhellipe.bookstore.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    @Transactional(readOnly=true)
    @Query("SELECT DISTINCT obj FROM Livro obj INNER JOIN obj.categorias cat WHERE obj.titulo LIKE %:titulo% AND cat IN :categorias")
    Page<Livro> findDistinctByNomeContainingAndCategoriasIn(@Param("titulo") String titulo, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
}
