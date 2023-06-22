package com.ribbtec.smartwallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.entity.Ativo;
import com.ribbtec.smartwallet.repository.AtivoRepository;

@Service
public class AtivoService {

	@Autowired
	private AtivoRepository ativoRepository;
	
	public List<Ativo> buscarTodos() {
		return ativoRepository.findAll();
	}
	
	public Ativo criar(Ativo ativo) {
		return ativoRepository.save(ativo);
	}

	public Ativo buscarPorId(String id) {
		
		Optional<Ativo> ativo = ativoRepository.findById(id);
		
		return ativo.isPresent() ? ativo.get() : null;
	}
}
