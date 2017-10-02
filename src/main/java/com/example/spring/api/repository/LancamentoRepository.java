package com.example.spring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
