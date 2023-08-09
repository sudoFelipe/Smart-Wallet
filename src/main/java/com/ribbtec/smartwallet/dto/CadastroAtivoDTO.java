package com.ribbtec.smartwallet.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroAtivoDTO(
		
		String id, 
		
		@NotBlank
		String descricao, 
		
//		@NotNull
		@Valid
		CadastroTipoAtivoDTO tipoAtivo, 
		
		@NotBlank
		String ticker , 
		
//		@NotNull
		@Valid
		CadastroEmpresaDTO instituicao ) {

}
