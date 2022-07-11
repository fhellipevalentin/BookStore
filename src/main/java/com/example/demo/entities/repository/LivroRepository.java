package com.example.demo.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{

}
