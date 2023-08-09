package com.ribbtec.smartwallet.dto;

import com.ribbtec.smartwallet.entity.TipoAtivo;

import jakarta.validation.constraints.NotBlank;

public record DadosTipoAtivoDTO(
		
		@NotBlank
		String id, 
		
		@NotBlank
		String descricao, 
		
		@NotBlank
		String sigla, 
		
		boolean ativo) {

	public DadosTipoAtivoDTO(TipoAtivo tipoAtivo) {
		
		this(tipoAtivo.getId(), tipoAtivo.getDescricao(), tipoAtivo.getSigla(), tipoAtivo.getAtivo());
	}
}
