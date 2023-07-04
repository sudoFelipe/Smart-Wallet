package com.ribbtec.smartwallet.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoTipoAtivoDTO(
		
		@NotNull
		String id, 
		String descricao, 
		String sigla) {
	
}
