package com.ribbtec.smartwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.dto.AtualizacaoTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.DadosTipoAtivoDTO;
import com.ribbtec.smartwallet.entity.TipoAtivo;
import com.ribbtec.smartwallet.repository.TipoAtivoRepository;

import jakarta.validation.Valid;

@Service
public class TipoAtivoService {

	@Autowired
	private TipoAtivoRepository tipoAtivoRepository;
	
	public List<DadosTipoAtivoDTO> buscarTodos(Pageable paginacao) {
		return tipoAtivoRepository.findAll(paginacao).stream().map(DadosTipoAtivoDTO::new).toList();
	}
	
	public List<DadosTipoAtivoDTO> buscarTodosAtivos(Pageable paginacao) {
		return tipoAtivoRepository.findAllByAtivoTrue(paginacao).stream().map(DadosTipoAtivoDTO::new).toList();
	}
	
	public DadosTipoAtivoDTO criar(@Valid CadastroTipoAtivoDTO dados) {
		
		TipoAtivo tipoAtivo = new TipoAtivo(dados);
		
		return new DadosTipoAtivoDTO(tipoAtivoRepository.save(tipoAtivo));
	}

	public Optional<DadosTipoAtivoDTO> buscarPorId(String id) {
		
		Optional<TipoAtivo> tipoAtivo = tipoAtivoRepository.findById(id);
		
		if (tipoAtivo.isPresent()) {
			
			return Optional.of(new DadosTipoAtivoDTO(tipoAtivo.get()));
		}
		
		return Optional.empty();
	}

	public Optional<DadosTipoAtivoDTO> atualizar(AtualizacaoTipoAtivoDTO dados) {
		
		var retorno = tipoAtivoRepository.findById(dados.id());
		
		if (retorno.isPresent()) {
			
			TipoAtivo retornoTipoAtivo = retorno.get();
			retornoTipoAtivo.atualizarDados(dados);
			retornoTipoAtivo = tipoAtivoRepository.save(retornoTipoAtivo);
			DadosTipoAtivoDTO dto = new DadosTipoAtivoDTO(retornoTipoAtivo);
			
			return Optional.of(dto);
		}
		
		return Optional.empty();
	}

	public Optional<DadosTipoAtivoDTO> remover(String id) {
		
		Optional<TipoAtivo> tipoAtivo = tipoAtivoRepository.findById(id);
		
		if (tipoAtivo.isPresent()) {
			
			TipoAtivo retorno = tipoAtivo.get();
			
			retorno.desativar();
			retorno = tipoAtivoRepository.save(retorno);
			
			DadosTipoAtivoDTO dto = new DadosTipoAtivoDTO(retorno);
			
			return Optional.of(dto);
		}
		
		return Optional.empty();
	}
}
