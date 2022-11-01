package io.github.fhellipe.bookstore;

import io.github.fhellipe.bookstore.model.Categoria;
import io.github.fhellipe.bookstore.model.Livro;
import io.github.fhellipe.bookstore.repositories.CategoriaRepository;
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
    }
}
