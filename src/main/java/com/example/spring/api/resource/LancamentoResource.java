package com.example.spring.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.api.event.RecursoCriadoEvent;
import com.example.spring.api.exceptionHandler.SpringExceptionHandler.Erro;
import com.example.spring.api.model.Lancamento;
import com.example.spring.api.repository.LancamentoRepository;
import com.example.spring.api.service.LancamentoService;
import com.example.spring.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<Lancamento> listar() {
		return lancamentoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		if(lancamento == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(lancamento);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento entity, HttpServletResponse response) {
		Lancamento save = lancamentoService.salvar(entity);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, save.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
	
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenv = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenv));
		
		return ResponseEntity.badRequest().body(erros);
	}

}
