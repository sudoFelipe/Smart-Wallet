package com.ribbtec.smartwallet.dto;

import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.entity.TipoAtivo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CadastroAtivoDTO(
		
		String id, 
		
		@NotBlank
		String descricao, 
		
		@NotBlank
		@Valid
		TipoAtivo tipoAtivo, 
		
		@NotBlank
		String ticker , 
		
		@NotBlank
		@Valid
		Empresa instituicao ) {

}
