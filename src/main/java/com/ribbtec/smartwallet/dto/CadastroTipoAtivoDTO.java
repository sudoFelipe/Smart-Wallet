package com.ribbtec.smartwallet.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroTipoAtivoDTO(
		
		String id, 
		
		@NotBlank(message = "{descricao.obrigatorio}")
		String descricao, 
		
		@NotBlank(message = "{sigla.obrigatorio}")
		String sigla) {

}
