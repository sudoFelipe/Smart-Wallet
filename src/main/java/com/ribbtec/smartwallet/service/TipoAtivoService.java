package com.ribbtec.smartwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.entity.TipoAtivo;
import com.ribbtec.smartwallet.repository.TipoAtivoRepository;

@Service
public class TipoAtivoService {

	@Autowired
	private TipoAtivoRepository tipoAtivoService;
	
	public List<TipoAtivo> buscarTodos() {
		return tipoAtivoService.findAll();
	}
	
	public TipoAtivo criar(TipoAtivo tipoAtivo) {
		return tipoAtivoService.save(tipoAtivo);
	}

	public TipoAtivo buscarPorId(String id) {
		
		Optional<TipoAtivo> tipoAtivo = tipoAtivoService.findById(id);
		
		return tipoAtivo.isPresent() ? tipoAtivo.get() : null;
	}
}
