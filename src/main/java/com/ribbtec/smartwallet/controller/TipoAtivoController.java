package com.ribbtec.smartwallet.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ribbtec.smartwallet.dto.AtualizacaoTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.DadosTipoAtivoDTO;
import com.ribbtec.smartwallet.service.TipoAtivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoAtivo")
public class TipoAtivoController {

	@Autowired
	private TipoAtivoService tipoAtivoService;
	
	@GetMapping
	public List<DadosTipoAtivoDTO> consultarTodos(@PageableDefault(size = 10, sort = {"descricao"}) Pageable paginacao) {
//		return tipoAtivoService.buscarTodos(paginacao);
		return tipoAtivoService.buscarTodosAtivos(paginacao);
	}
	
	@GetMapping("/{id}")
	public DadosTipoAtivoDTO consultarPorId(String id) {
		return tipoAtivoService.buscarPorId(id);
	}
	
	@PostMapping
	@Transactional
	public DadosTipoAtivoDTO incluir(@RequestBody @Valid CadastroTipoAtivoDTO dados) {
		return tipoAtivoService.criar(dados);
	}
	
	@PutMapping
	@Transactional
	public DadosTipoAtivoDTO alterar(@RequestParam @Valid AtualizacaoTipoAtivoDTO dados) {
		return tipoAtivoService.atualizar(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public Optional<DadosTipoAtivoDTO> excluir(@PathVariable String id) {
		return tipoAtivoService.remover(id);
	}
}
