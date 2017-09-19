package com.example.spring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
