package com.example.spring.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.spring.api.model.Lancamento;
import com.example.spring.api.repository.filter.LancamentoFilter;


public interface LancamentoRepositoryQuery {

	Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
