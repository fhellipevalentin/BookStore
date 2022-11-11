package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.enums.EstadoPagamento;
import io.github.fhellipe.bookstore.model.ItemPedido;
import io.github.fhellipe.bookstore.model.PagamentoComBoleto;
import io.github.fhellipe.bookstore.model.Pedido;
import io.github.fhellipe.bookstore.repositories.ItemPedidoRepository;
import io.github.fhellipe.bookstore.repositories.PagamentoRepository;
import io.github.fhellipe.bookstore.repositories.PedidoRepository;
import io.github.fhellipe.bookstore.repositories.UsuarioRepository;
import io.github.fhellipe.bookstore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não Encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(LocalDateTime.now());
        obj.setUsuario(usuarioService.find(obj.getUsuario().getId()));
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setLivro(livroService.find(ip.getLivro().getId()));
            ip.setPreco(ip.getLivro().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }
}
