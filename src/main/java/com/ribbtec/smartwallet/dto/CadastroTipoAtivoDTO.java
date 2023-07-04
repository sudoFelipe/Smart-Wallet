package com.ribbtec.smartwallet.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroTipoAtivoDTO(
		
		String id, 
		
		@NotBlank
		String descricao, 
		
		@NotBlank
		String sigla) {

}
