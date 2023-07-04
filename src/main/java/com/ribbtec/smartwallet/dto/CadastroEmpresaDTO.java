package com.ribbtec.smartwallet.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroEmpresaDTO(
		
		
		String id, 
		
		@NotBlank
		String descricao, 
		
		@NotBlank
		String cnpj) {

}
