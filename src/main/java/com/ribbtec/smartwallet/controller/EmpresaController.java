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

import com.ribbtec.smartwallet.dto.AtualizacaoEmpresaDTO;
import com.ribbtec.smartwallet.dto.CadastroEmpresaDTO;
import com.ribbtec.smartwallet.dto.DadosEmpresaDTO;
import com.ribbtec.smartwallet.service.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public List<DadosEmpresaDTO> consultarTodos(@PageableDefault(size = 10, sort = "descricao") Pageable paginacao) {
//		return empresaService.buscarTodos();
		return empresaService.buscarTodosAtivos(paginacao);
	}
	
	@GetMapping("/{id}")
	public DadosEmpresaDTO consultarPorId(@PathVariable String id) {
		return empresaService.buscarPorId(id);
	}
	
	@PostMapping
	@Transactional
	public DadosEmpresaDTO incluir(@RequestBody @Valid CadastroEmpresaDTO dados) {
		return empresaService.criar(dados);
	}
	
	@PutMapping
	@Transactional
	public DadosEmpresaDTO alterar(@RequestBody @Valid AtualizacaoEmpresaDTO dados) {
		return empresaService.atualizar(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public DadosEmpresaDTO excluir(@PathVariable String id) {
		return empresaService.remover(id);
	}
}
