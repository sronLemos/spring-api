package com.example.spring.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.api.event.RecursoCriadoEvent;
import com.example.spring.api.model.Pessoa;
import com.example.spring.api.repository.PessoaRepository;
import com.example.spring.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired 
	private PessoaService pessoaService;
	
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa entity, HttpServletResponse response) {
		Pessoa save = pessoaRepository.save(entity);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, save.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		if(pessoa == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(pessoa);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa entity) {
		
		return ResponseEntity.<Pessoa>ok(pessoaService.atualizar(codigo, entity));
	}
	@PutMapping("/{codigo}/endereco")
	public ResponseEntity<Pessoa> atualizarEndereco(@PathVariable Long codigo, @Valid @RequestBody Pessoa entity) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		
		
		BeanUtils.copyProperties(entity, pessoa, "codigo");
		pessoaRepository.save(pessoa);
		
		return ResponseEntity.<Pessoa>ok(pessoa);
	}
}
