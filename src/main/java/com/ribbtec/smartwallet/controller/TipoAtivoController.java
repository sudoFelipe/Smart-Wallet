package com.ribbtec.smartwallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribbtec.smartwallet.entity.TipoAtivo;
import com.ribbtec.smartwallet.record.InfoTipoAtivo;
import com.ribbtec.smartwallet.service.TipoAtivoService;

@RestController
@RequestMapping("/tipoAtivo")
public class TipoAtivoController {

	@Autowired
	private TipoAtivoService tipoAtivoService;
	
	@GetMapping
	public List<TipoAtivo> consultarTodos() {
		return tipoAtivoService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public TipoAtivo consultarPorId(String id) {
		return tipoAtivoService.buscarPorId(id);
	}
	
	@PostMapping
	@Transactional
	public TipoAtivo incluir(@RequestBody InfoTipoAtivo dados) {
		return tipoAtivoService.criar(new TipoAtivo(dados));
	}
}
