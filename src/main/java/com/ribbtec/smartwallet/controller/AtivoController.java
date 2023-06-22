package com.ribbtec.smartwallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribbtec.smartwallet.entity.Ativo;
import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.entity.TipoAtivo;
import com.ribbtec.smartwallet.service.AtivoService;
import com.ribbtec.smartwallet.service.EmpresaService;
import com.ribbtec.smartwallet.service.TipoAtivoService;

@RestController
@RequestMapping("/ativo")
public class AtivoController {

	@Autowired
	private AtivoService ativoService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private TipoAtivoService tipoAtivoService;
	
	@GetMapping
	public List<Ativo> consultarTodos() {
		return ativoService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public Ativo consultarPorId(String id) {
		return ativoService.buscarPorId(id);
	}
	
	@PostMapping
	@Transactional
	public Ativo incluirAtivo(@RequestBody Ativo dados) {
		
		Empresa empresa = empresaService.buscarPorId("647d20c807a921005138e9fc");
		TipoAtivo tipo = tipoAtivoService.buscarPorId("647d1bf91e583032376860f3");
		
		dados.setInstituicao(empresa);
		dados.setTipoAtivo(tipo);
		
		return ativoService.criar(dados);
	}
}
