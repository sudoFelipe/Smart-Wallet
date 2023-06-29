package com.ribbtec.smartwallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribbtec.smartwallet.dto.DadosTipoAtivoDTO;
import com.ribbtec.smartwallet.entity.TipoAtivo;
import com.ribbtec.smartwallet.record.InfoTipoAtivo;
import com.ribbtec.smartwallet.service.TipoAtivoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoAtivo")
public class TipoAtivoController {

	@Autowired
	private TipoAtivoService tipoAtivoService;
	
	@GetMapping
	public List<DadosTipoAtivoDTO> consultarTodos() {
		return tipoAtivoService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public DadosTipoAtivoDTO consultarPorId(String id) {
		return tipoAtivoService.buscarPorId(id);
	}
	
	@PostMapping
	@Transactional
	public DadosTipoAtivoDTO incluir(@RequestBody @Valid InfoTipoAtivo dados) {
		return tipoAtivoService.criar(new TipoAtivo(dados));
	}
}
