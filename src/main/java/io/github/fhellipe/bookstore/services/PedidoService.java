package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.model.Pedido;
import io.github.fhellipe.bookstore.repositories.PedidoRepository;
import io.github.fhellipe.bookstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não Encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
