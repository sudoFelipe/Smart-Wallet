package com.ribbtec.smartwallet.record;

import jakarta.validation.constraints.NotBlank;

public record InfoTipoAtivo(
		
		String id, 
		
		@NotBlank
		String descricao, 
		
		@NotBlank
		String sigla) {

}
