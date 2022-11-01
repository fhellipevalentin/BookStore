package io.github.fhellipe.bookstore;

import io.github.fhellipe.bookstore.model.Categoria;
import io.github.fhellipe.bookstore.model.Cidade;
import io.github.fhellipe.bookstore.model.Estado;
import io.github.fhellipe.bookstore.model.Livro;
import io.github.fhellipe.bookstore.repositories.CategoriaRepository;
import io.github.fhellipe.bookstore.repositories.CidadeRepository;
import io.github.fhellipe.bookstore.repositories.EstadoRepository;
import io.github.fhellipe.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    }
}
