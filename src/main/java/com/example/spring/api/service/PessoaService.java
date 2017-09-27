package com.example.spring.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.spring.api.model.Pessoa;
import com.example.spring.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa entity) {
		Pessoa pessoa = buscarPessoaPorCodigo(codigo);
		
		BeanUtils.copyProperties(entity, pessoa, "codigo");
		return pessoaRepository.save(pessoa);
	}


	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoa = buscarPessoaPorCodigo(codigo);
		pessoa.setAtivo(ativo);
		pessoaRepository.save(pessoa);
	}

	private Pessoa buscarPessoaPorCodigo(Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		
		if(pessoa ==  null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoa;
	}
}
