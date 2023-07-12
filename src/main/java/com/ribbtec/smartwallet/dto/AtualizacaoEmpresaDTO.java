package com.ribbtec.smartwallet.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoEmpresaDTO(
		
		@NotNull
		String id,
		String descricao,
		String cnpj) {

}
