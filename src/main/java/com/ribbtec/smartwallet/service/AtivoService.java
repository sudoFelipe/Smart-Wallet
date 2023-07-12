package com.ribbtec.smartwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.dto.AtualizacaoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroAtivoDTO;
import com.ribbtec.smartwallet.dto.DadosAtivoDTO;
import com.ribbtec.smartwallet.entity.Ativo;
import com.ribbtec.smartwallet.repository.AtivoRepository;

import jakarta.validation.Valid;

@Service
public class AtivoService {

	@Autowired
	private AtivoRepository ativoRepository;
	
	public List<DadosAtivoDTO> buscarTodos(Pageable paginacao) {
		return ativoRepository.findAll(paginacao).stream().map(DadosAtivoDTO::new).toList();
	}
	
	public List<DadosAtivoDTO> buscarTodosAtivos(Pageable paginacao) {
		return ativoRepository.findByAtivoTrue(paginacao).stream().map(DadosAtivoDTO::new).toList();
	}
	
	public DadosAtivoDTO criar(CadastroAtivoDTO dados) {
		
		Ativo ativo = new Ativo(dados);
		
		return new DadosAtivoDTO(ativoRepository.save(ativo));
	}

	public DadosAtivoDTO buscarPorId(String id) {
		
		Optional<Ativo> ativo = ativoRepository.findById(id);
		
		return ativo.isPresent() ? new DadosAtivoDTO(ativo.get()) : null;
	}

	public DadosAtivoDTO atualizar(@Valid AtualizacaoAtivoDTO dados) {
		
		Optional<Ativo> retornoAtivo = ativoRepository.findById(dados.id());
		
		if (retornoAtivo.isPresent()) {
			Ativo ativo = retornoAtivo.get();
			ativo.atulizarDados(dados);
			ativo = ativoRepository.save(ativo);
			
			return new DadosAtivoDTO(ativo);
		}
		
		return null;
	}

	public DadosAtivoDTO remover(String id) {
		
		Optional<Ativo> retornoAtivo = ativoRepository.findById(id);
		
		if (retornoAtivo.isPresent()) {
			Ativo ativo = retornoAtivo.get();
			ativo.desativar();
			return new DadosAtivoDTO(ativoRepository.save(ativo));
		}
		
		return null;
	}
}
