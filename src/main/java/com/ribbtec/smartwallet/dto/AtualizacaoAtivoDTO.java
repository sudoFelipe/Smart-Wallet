package com.ribbtec.smartwallet.dto;

import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.entity.TipoAtivo;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoAtivoDTO(
		
		@NotNull
		String id,
		
		String descricao,
		
		TipoAtivo tipoAtivo,
		
		String ticker,
		
		Empresa instituicao) {

}
