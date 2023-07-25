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
	
	public Optional<DadosAtivoDTO> buscarPorId(String id) {
		
		Optional<Ativo> ativo = ativoRepository.findById(id);
		
		if (ativo.isPresent()) {
			
			var retorno = new DadosAtivoDTO(ativo.get());
			
			Optional.of(retorno);
		}
		
		return Optional.empty();
	}
	
	public DadosAtivoDTO criar(CadastroAtivoDTO dados) {
		
		Ativo ativo = new Ativo(dados);
		
		return new DadosAtivoDTO(ativoRepository.save(ativo));
	}

	public Optional<DadosAtivoDTO> atualizar(@Valid AtualizacaoAtivoDTO dados) {
		
		Optional<Ativo> retornoAtivo = ativoRepository.findById(dados.id());
		
		if (retornoAtivo.isPresent()) {
			Ativo ativo = retornoAtivo.get();
			ativo.atulizarDados(dados);
			ativo = ativoRepository.save(ativo);
			
			DadosAtivoDTO dto = new DadosAtivoDTO(ativo);
			
			return Optional.of(dto);
		}
		
		return Optional.empty();
	}

	public Optional<DadosAtivoDTO> remover(String id) {
		
		Optional<Ativo> retornoAtivo = ativoRepository.findById(id);
		
		if (retornoAtivo.isPresent()) {
			Ativo ativo = retornoAtivo.get();
			ativo.desativar();
			DadosAtivoDTO dto = new DadosAtivoDTO(ativoRepository.save(ativo));
			
			return Optional.of(dto);
		}
		
		return Optional.empty();
	}
}
