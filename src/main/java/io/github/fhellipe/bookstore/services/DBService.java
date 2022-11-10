package io.github.fhellipe.bookstore.services;

import io.github.fhellipe.bookstore.enums.EstadoPagamento;
import io.github.fhellipe.bookstore.enums.TipoUsuario;
import io.github.fhellipe.bookstore.model.*;
import io.github.fhellipe.bookstore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import io.github.fhellipe.bookstore.enums.EstadoPagamento;
import io.github.fhellipe.bookstore.enums.TipoUsuario;
import io.github.fhellipe.bookstore.model.*;
import io.github.fhellipe.bookstore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DBService {

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

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public void instantiateTestDatabase() throws ParseException {

        Categoria cat1 = new Categoria(null, "Economia");
        Categoria cat2 = new Categoria(null, "Teórico");
        Categoria cat3 = new Categoria(null, "Literatura Jurídica");
        Categoria cat4 = new Categoria(null, "Biografias");

        Livro l1 = new Livro(null, "Juridico e Minha vida", "Jurencio", 80.00);
        Livro l2 = new Livro(null, "As Cronicas do Comércio", "Comerciante Anônimo", 24.00);
        Livro l3 = new Livro(null, "Suits, uma licao para a Vida", "Maguire Goodman", 56.00);
        Livro l4 = new Livro(null, "O Júri", "John Grisham", 50.60);
        Livro l5 = new Livro(null, "O Primeiro Ano: Como Se Faz Um Advogado", "Scott Turow", 47.40);
        Livro l6 = new Livro(null, "Justiça: o que é a coisa certa a fazer", "Michael J. Sandel", 33.80);
        Livro l7 = new Livro(null, "Contra o sistema da corrupção", "Sergio Moro", 29.99);
        Livro l8 = new Livro(null, "Desapropriação", "Manoel de Oliveira Franco Sobrinho", 104.00);
        Livro l9 = new Livro(null, "Manual de Direito Penal", "Guilherme de Souza Nucci", 227.98);
        Livro l10 = new Livro(null, "Direito Civil Brasileiro", "Carlos Roberto Gonçalves", 82.80);
        Livro l11 = new Livro(null, "Direito Digital e Proteção de Dados", "Patricia Peck Pinheiro", 165.95);
        Livro l12 = new Livro(null, "Clóvis Beviláqua: sua vida, sua obra", "Sílvio Meira", 30.00);
        Livro l13 = new Livro(null, "Ação Humana", "Ludwing Von Mises", 249.90);
        Livro l14 = new Livro(null, "The Theory of Money and Credit", "Ludwing Von Mises", 156.00);
        Livro l15 = new Livro(null, "A Teoria Comunista do Direito", "Hans Kelsen", 37.40);

        l1.getCategorias().addAll(Arrays.asList(cat2));
        l2.getCategorias().addAll(Arrays.asList(cat1));
        l3.getCategorias().addAll(Arrays.asList(cat2));
        l4.getCategorias().addAll(Arrays.asList(cat3));
        l5.getCategorias().addAll(Arrays.asList(cat2));
        l6.getCategorias().addAll(Arrays.asList(cat2));
        l7.getCategorias().addAll(Arrays.asList(cat4));
        l8.getCategorias().addAll(Arrays.asList(cat2));
        l9.getCategorias().addAll(Arrays.asList(cat2));
        l10.getCategorias().addAll(Arrays.asList(cat2));
        l11.getCategorias().addAll(Arrays.asList(cat2));
        l12.getCategorias().addAll(Arrays.asList(cat4));
        l13.getCategorias().addAll(Arrays.asList(cat1));
        l14.getCategorias().addAll(Arrays.asList(cat1));
        l15.getCategorias().addAll(Arrays.asList(cat3));


        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
        livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l4, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15));

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

        usr1.getEnderecos().addAll(Arrays.asList(e1, e2));

        usuarioRepository.saveAll(Arrays.asList(usr1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        Pedido ped1 = new Pedido(null, LocalDateTime.parse("22/11/2022 10:35:10", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), usr1, e1);
        Pedido ped2 = new Pedido(null, LocalDateTime.parse("20/11/2022 11:12:02", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), usr1, e2);

        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null, LocalDateTime.parse("23/11/2022 11:12:02", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        ped1.setPagamento(pgto1);
        ped2.setPagamento(pgto2);

        usr1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

        ItemPedido ip1 = new ItemPedido(ped1, l1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, l3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, l2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        l1.getItens().addAll(Arrays.asList(ip1));
        l2.getItens().addAll(Arrays.asList(ip3));
        l3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
