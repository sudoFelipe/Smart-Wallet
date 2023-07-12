package com.ribbtec.smartwallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribbtec.smartwallet.dto.AtualizacaoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroAtivoDTO;
import com.ribbtec.smartwallet.dto.DadosAtivoDTO;
import com.ribbtec.smartwallet.service.AtivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ativo")
public class AtivoController {

	@Autowired
	private AtivoService ativoService;
	
	@GetMapping
	public List<DadosAtivoDTO> consultarTodos(@PageableDefault(size = 10, sort = "ticker") Pageable paginacao) {
//		return ativoService.buscarTodos(paginacao);
		return ativoService.buscarTodosAtivos(paginacao);
	}
	
	@GetMapping("/{id}")
	public DadosAtivoDTO consultarPorId(String id) {
		return ativoService.buscarPorId(id);
	}
	
	@PostMapping
	@Transactional
	public DadosAtivoDTO incluir(@RequestBody @Valid CadastroAtivoDTO dados) {
		
		return ativoService.criar(dados);
	}
	
	@PutMapping
	@Transactional
	public DadosAtivoDTO alterar(@RequestBody @Valid AtualizacaoAtivoDTO dados) {
		return ativoService.atualizar(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public DadosAtivoDTO excluir(@PathVariable String id) {
		return ativoService.remover(id);
	}
}
