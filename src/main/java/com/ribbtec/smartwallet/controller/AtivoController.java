package com.ribbtec.smartwallet.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ribbtec.smartwallet.dto.AtualizacaoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroAtivoDTO;
import com.ribbtec.smartwallet.dto.DadosAtivoDTO;
import com.ribbtec.smartwallet.infra.exception.TratadorDeErrosAtivo;
import com.ribbtec.smartwallet.service.AtivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ativo")
public class AtivoController {

	@Autowired
	private AtivoService ativoService;
	
	@GetMapping
	public ResponseEntity<List<DadosAtivoDTO>> consultarTodos(@PageableDefault(size = 10, sort = "ticker") Pageable paginacao) {
		
		var retorno = ativoService.buscarTodosAtivos(paginacao);
		
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosAtivoDTO> consultarPorId(String id) {
		
		var retorno = ativoService.buscarPorId(id);
		
		return tratarRetorno(retorno);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosAtivoDTO> incluir(@RequestBody @Valid CadastroAtivoDTO dados, UriComponentsBuilder uriBuilder) {
		
		var retorno = ativoService.criar(dados);
		
		URI uri = uriBuilder.path("/ativo/{id}").buildAndExpand(retorno.id()).toUri();
		
		return ResponseEntity.created(uri).body(retorno);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosAtivoDTO> alterar(@RequestBody @Valid AtualizacaoAtivoDTO dados) {
		
		var retorno = ativoService.atualizar(dados);
		
		return tratarRetorno(retorno);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosAtivoDTO> excluir(@PathVariable String id) {
		var retorno = ativoService.remover(id);
		
		return tratarRetorno(retorno);
	}
	
	private ResponseEntity<DadosAtivoDTO> tratarRetorno(Optional<DadosAtivoDTO> retorno) {
		
		if (retorno.isPresent()) {
			return ResponseEntity.ok(retorno.get());
		}
		
		return TratadorDeErrosAtivo.tratamentoErro404();
	}
}
