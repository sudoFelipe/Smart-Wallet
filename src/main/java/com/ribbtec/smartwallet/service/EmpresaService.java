package com.ribbtec.smartwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.dto.DadosEmpresaDTO;
import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	//	BUSCA DE TODAS AS EMPRESAS
	public List<DadosEmpresaDTO> buscarTodos() {
		//	Converte as entidades em DTO's
		return empresaRepository.findAll().stream().map(DadosEmpresaDTO::new).toList();
	}
	
	//	CRIACAO DE EMPRESA
	public Empresa criar(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	//	BUSCA DE EMPRESA POR ID
	public Empresa buscarPorId(String id) {
		
		Optional<Empresa> empresa = empresaRepository.findById(id);
		
		return empresa.isPresent() ? empresa.get() : null;
	}
}
