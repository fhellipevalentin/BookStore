package io.github.fhellipe.bookstore;

import io.github.fhellipe.bookstore.model.Categoria;
import io.github.fhellipe.bookstore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BookStoreApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Comércio");
        Categoria cat2 = new Categoria(null, "Jurídicos");

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    }
}
