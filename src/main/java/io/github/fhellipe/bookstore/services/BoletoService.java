package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.model.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDateTime instanteDoPedido) {
        LocalDateTime cal = LocalDateTime.now();
        cal.adjustInto(instanteDoPedido);
        LocalDateTime maisSeteDias = cal.plusWeeks(1);
        pagto.setDataDeVencimento(maisSeteDias);
    }
}
