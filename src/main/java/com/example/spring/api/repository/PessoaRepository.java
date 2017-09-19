package com.example.spring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
