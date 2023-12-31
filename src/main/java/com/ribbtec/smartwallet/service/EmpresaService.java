package com.ribbtec.smartwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.dto.AtualizacaoEmpresaDTO;
import com.ribbtec.smartwallet.dto.CadastroEmpresaDTO;
import com.ribbtec.smartwallet.dto.DadosEmpresaDTO;
import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.repository.EmpresaRepository;

import jakarta.validation.Valid;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	//	BUSCA DE TODAS AS EMPRESAS
	public List<DadosEmpresaDTO> buscarTodos(Pageable paginacao) {
		//	Converte as entidades em DTO's
		return empresaRepository.findAll().stream().map(DadosEmpresaDTO::new).toList();
	}

	//	BUSCA DAS EMPRESAS ATIVAS
	public List<DadosEmpresaDTO> buscarTodosAtivos(Pageable paginacao) {
		//	Converte as entidades em DTO's
		return empresaRepository.findByAtivoTrue(paginacao).stream().map(DadosEmpresaDTO::new).toList();
	}
	
	//	BUSCA DE EMPRESA POR ID
	public Optional<DadosEmpresaDTO> buscarPorId(String id) {
		
		Optional<Empresa> empresa = empresaRepository.findById(id);
		
		if (empresa.isPresent()) {
			DadosEmpresaDTO dto = new DadosEmpresaDTO(empresa.get());
			return Optional.of(dto);
		}
		
		return Optional.empty();
	}
	
	//	CRIACAO DE EMPRESA
	public DadosEmpresaDTO criar(@Valid CadastroEmpresaDTO dados) {
		
		Empresa empresa = new Empresa(dados);
		
		return new DadosEmpresaDTO(empresaRepository.save(empresa));
	}

	//	ATUALIZACAO DE EMPRESA
	public Optional<DadosEmpresaDTO> atualizar(@Valid AtualizacaoEmpresaDTO dados) {
		
		Optional<Empresa> retornoEmpresa = empresaRepository.findById(dados.id());
		
		if (retornoEmpresa.isPresent()) {
			Empresa empresa = retornoEmpresa.get();
			empresa.atualizarDados(dados);
			DadosEmpresaDTO dto = new DadosEmpresaDTO(empresaRepository.save(empresa));
			return Optional.of(dto);
		}
		
		return Optional.empty();
	}

	//	 REMOCAO DE EMPRESA
	public Optional<DadosEmpresaDTO> remover(String id) {
		
		Optional<Empresa> retornoEmpresa = empresaRepository.findById(id);
		
		if (retornoEmpresa.isPresent()) {
			Empresa empresa = retornoEmpresa.get();
			empresa.desativar();
			
			DadosEmpresaDTO dto = new DadosEmpresaDTO(empresaRepository.save(empresa));
			return Optional.of(dto);
		}
		
		return Optional.empty();
	}

}
