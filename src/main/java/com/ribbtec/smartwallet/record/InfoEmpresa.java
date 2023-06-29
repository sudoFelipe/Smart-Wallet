package com.ribbtec.smartwallet.record;

import jakarta.validation.constraints.NotBlank;

public record InfoEmpresa(
		
		
		String id, 
		
		@NotBlank
		String descricao, 
		
		@NotBlank
		String cnpj) {

}
