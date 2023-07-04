package com.ribbtec.smartwallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribbtec.smartwallet.dto.DadosTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.AtualizacaoTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroTipoAtivoDTO;
import com.ribbtec.smartwallet.entity.TipoAtivo;
import com.ribbtec.smartwallet.service.TipoAtivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoAtivo")
public class TipoAtivoController {

	@Autowired
	private TipoAtivoService tipoAtivoService;
	
	@GetMapping
	public List<DadosTipoAtivoDTO> consultarTodos(@PageableDefault(size = 10, page = 1) Pageable paginacao) {
		return tipoAtivoService.buscarTodos(paginacao);
	}
	
	@GetMapping("/{id}")
	public DadosTipoAtivoDTO consultarPorId(String id) {
		return tipoAtivoService.buscarPorId(id);
	}
	
	@PostMapping
	@Transactional
	public DadosTipoAtivoDTO incluir(@RequestBody @Valid CadastroTipoAtivoDTO dados) {
		return tipoAtivoService.criar(new TipoAtivo(dados));
	}
	
	@PutMapping
	@Transactional
	public DadosTipoAtivoDTO atualizar(@RequestBody @Valid AtualizacaoTipoAtivoDTO dados) {
		return tipoAtivoService.alterar(dados);
	}
}
