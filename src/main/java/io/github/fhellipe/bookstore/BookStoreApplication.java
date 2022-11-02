package io.github.fhellipe.bookstore;

import io.github.fhellipe.bookstore.enums.EstadoPagamento;
import io.github.fhellipe.bookstore.enums.TipoUsuario;
import io.github.fhellipe.bookstore.model.*;
import io.github.fhellipe.bookstore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Comércio");
        Categoria cat2 = new Categoria(null, "Jurídicos");

        Livro l1 = new Livro(null, "Juridico e Minha vida", "Jurencio", 80.00);
        Livro l2 = new Livro(null, "As Cronicas do Comércio", "Comerciante Anônimo", 24.00);
        Livro l3 = new Livro(null, "Suits, uma licao para a Vida", "Maguire Goodman", 56.00);

        l1.getCategorias().addAll(Arrays.asList(cat2));
        l2.getCategorias().addAll(Arrays.asList(cat1));
        l3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        livroRepository.saveAll(Arrays.asList(l1, l2, l3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Usuario usr1 = new Usuario(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoUsuario.PESSOAFISICA);
        usr1.getTelefones().addAll(Arrays.asList("927363323", "993838393"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", usr1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", usr1, c2);

        usr1.getEnderecos().addAll(Arrays.asList(e1,e2));

        usuarioRepository.saveAll(Arrays.asList(usr1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));

        Pedido ped1 = new Pedido(null, LocalDateTime.parse("22/11/2022 10:35:10", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), usr1, e1);
        Pedido ped2 = new Pedido(null, LocalDateTime.parse("20/11/2022 11:12:02", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), usr1, e2);

        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null, LocalDateTime.parse("23/11/2022 11:12:02", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        ped1.setPagamento(pgto1);
        ped2.setPagamento(pgto2);

        usr1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
    }
}
