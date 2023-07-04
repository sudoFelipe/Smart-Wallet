package com.ribbtec.smartwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.dto.AtualizacaoTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.DadosTipoAtivoDTO;
import com.ribbtec.smartwallet.entity.TipoAtivo;
import com.ribbtec.smartwallet.repository.TipoAtivoRepository;

import jakarta.validation.Valid;

@Service
public class TipoAtivoService {

	@Autowired
	private TipoAtivoRepository tipoAtivoRepository;
	
	public List<DadosTipoAtivoDTO> buscarTodos(Pageable paginacao) {
		return tipoAtivoRepository.findAll().stream().map(DadosTipoAtivoDTO::new).toList();
	}
	
	public DadosTipoAtivoDTO criar(TipoAtivo tipoAtivo) {
		return new DadosTipoAtivoDTO(tipoAtivoRepository.save(tipoAtivo));
	}

	public DadosTipoAtivoDTO buscarPorId(String id) {
		
		Optional<TipoAtivo> tipoAtivo = tipoAtivoRepository.findById(id);
		
		return tipoAtivo.isPresent() ? new DadosTipoAtivoDTO(tipoAtivo.get()) : null;
	}

	public DadosTipoAtivoDTO alterar(AtualizacaoTipoAtivoDTO dados) {
		
		var retorno = tipoAtivoRepository.findById(dados.id());
		
		if (retorno.isPresent()) {
			
			TipoAtivo retornoTipoAtivo = retorno.get();
			retornoTipoAtivo.atualizarDados(dados);
			retornoTipoAtivo = tipoAtivoRepository.save(retornoTipoAtivo);
			return new DadosTipoAtivoDTO(retornoTipoAtivo);
		}
		
		return null;
	}
}
