package com.ribbtec.smartwallet.dto;

import com.ribbtec.smartwallet.entity.TipoAtivo;

public record DadosTipoAtivoDTO(String id, String descricao, String sigla) {

	public DadosTipoAtivoDTO(TipoAtivo tipoAtivo) {
		
		this(tipoAtivo.getId(), tipoAtivo.getDescricao(), tipoAtivo.getSigla());
	}
}
