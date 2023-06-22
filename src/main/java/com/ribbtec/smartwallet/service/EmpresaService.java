package com.ribbtec.smartwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public List<Empresa> buscarTodos() {
		return empresaRepository.findAll();
	}
	
	public Empresa criar(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	public Empresa buscarPorId(String id) {
		
		Optional<Empresa> empresa = empresaRepository.findById(id);
		
		return empresa.isPresent() ? empresa.get() : null;
	}
}
