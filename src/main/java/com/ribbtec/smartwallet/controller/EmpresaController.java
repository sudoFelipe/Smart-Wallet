package com.ribbtec.smartwallet.controller;

import java.util.List;

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

import com.ribbtec.smartwallet.dto.AtualizacaoEmpresaDTO;
import com.ribbtec.smartwallet.dto.CadastroEmpresaDTO;
import com.ribbtec.smartwallet.dto.DadosEmpresaDTO;
import com.ribbtec.smartwallet.infra.exception.TratadorDeErros;
import com.ribbtec.smartwallet.service.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public ResponseEntity<List<DadosEmpresaDTO>> consultarTodos(@PageableDefault(size = 10, sort = "descricao") Pageable paginacao) {
//		return empresaService.buscarTodos();
		var retorno = empresaService.buscarTodosAtivos(paginacao);
		
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosEmpresaDTO> consultarPorId(@PathVariable String id) {
		
		var retorno = empresaService.buscarPorId(id);
		
		if (retorno.isPresent()) {
			return ResponseEntity.ok(retorno.get());
		}
		
		return TratadorDeErros.tratamentoErro404();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosEmpresaDTO> incluir(@RequestBody @Valid CadastroEmpresaDTO dados, UriComponentsBuilder uriBuilder) {
		
		var retorno = empresaService.criar(dados);
		
		/*
		 * ENCAPSULAMENTO DA URI
		 * CONTEXT / CAMINHO / SUBSTITUICAO DO ID
		 */
		var uri = uriBuilder.path("/empresa/{id}").buildAndExpand(dados.id()).toUri();
		
		return ResponseEntity.created(uri).body(retorno);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosEmpresaDTO> alterar(@RequestBody @Valid AtualizacaoEmpresaDTO dados) {
		
		var retorno = empresaService.atualizar(dados);
		
		if (retorno.isPresent()) {
			return ResponseEntity.ok(retorno.get());
		}
		
		return TratadorDeErros.tratamentoErro404();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosEmpresaDTO> excluir(@PathVariable String id) {
		
		var retorno = empresaService.remover(id);
		
		if (retorno.isPresent()) {
			return ResponseEntity.ok().build();
		}
		
		return TratadorDeErros.tratamentoErro404();
	}
}
