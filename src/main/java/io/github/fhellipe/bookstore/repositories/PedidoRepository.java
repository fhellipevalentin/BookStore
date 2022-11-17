package io.github.fhellipe.bookstore.repositories;

import io.github.fhellipe.bookstore.model.Pedido;
import io.github.fhellipe.bookstore.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
    @Transactional(readOnly=true)
    Page<Pedido> findByUsuario(Usuario usuario, Pageable pageRequest);
}
