package com.ribbtec.smartwallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.record.InfoEmpresa;
import com.ribbtec.smartwallet.service.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public List<Empresa> consultarTodos() {
		return empresaService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public Empresa consultarPorId(String id) {
		return empresaService.buscarPorId(id);
	}
	
	@PostMapping
	@Transactional
	public Empresa incluir(@RequestBody InfoEmpresa dados) {
		return empresaService.criar(new Empresa(dados));
	}
}
