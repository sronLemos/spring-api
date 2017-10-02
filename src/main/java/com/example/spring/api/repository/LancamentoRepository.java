package com.example.spring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.api.model.Lancamento;
import com.example.spring.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
