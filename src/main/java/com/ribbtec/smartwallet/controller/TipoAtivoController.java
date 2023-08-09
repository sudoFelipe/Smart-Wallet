package com.ribbtec.smartwallet.controller;

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

import com.ribbtec.smartwallet.dto.AtualizacaoTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.DadosTipoAtivoDTO;
import com.ribbtec.smartwallet.infra.exception.TratadorDeErrosTipoAtivo;
import com.ribbtec.smartwallet.service.TipoAtivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoAtivo")
public class TipoAtivoController {

	@Autowired
	private TipoAtivoService tipoAtivoService;
	
	@GetMapping
	public ResponseEntity<List<DadosTipoAtivoDTO>> consultarTodos(@PageableDefault(size = 10, sort = {"descricao"}) Pageable paginacao) {
		
		var retorno = tipoAtivoService.buscarTodos(paginacao);
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosTipoAtivoDTO> consultarPorId(@PathVariable String id) {
		
		var retorno =  tipoAtivoService.buscarPorId(id);
		
		return validarRetorno(retorno);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosTipoAtivoDTO> incluir(@RequestBody @Valid CadastroTipoAtivoDTO dados, UriComponentsBuilder uriBuilder) {
		
		var retorno = tipoAtivoService.criar(dados);
		
		var uri = uriBuilder.path("/tipoativo/{id}").buildAndExpand(dados.id()).toUri();
		
		return ResponseEntity.created(uri).body(retorno);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosTipoAtivoDTO> alterar(@RequestBody @Valid AtualizacaoTipoAtivoDTO dados) {
		
		var retorno = tipoAtivoService.atualizar(dados);
		
		return validarRetorno(retorno);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosTipoAtivoDTO> excluir(@PathVariable String id) {
		
		var retorno = tipoAtivoService.remover(id);
		
		return validarRetorno(retorno);
	}
	
	private ResponseEntity<DadosTipoAtivoDTO> validarRetorno(Optional<DadosTipoAtivoDTO> retorno) {
		
		if (retorno.isPresent()) {
			
			return ResponseEntity.ok(retorno.get());
		}
		
		return TratadorDeErrosTipoAtivo.tratamentoErro404();
	}
}
