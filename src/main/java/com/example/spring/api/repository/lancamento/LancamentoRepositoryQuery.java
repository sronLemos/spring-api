package com.example.spring.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.spring.api.model.Lancamento;
import com.example.spring.api.repository.filter.LancamentoFilter;
import com.example.spring.api.repository.projection.ResumoLancamento;


public interface LancamentoRepositoryQuery {

	Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
