package com.ribbtec.smartwallet.record;

import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.entity.TipoAtivo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record InfoAtivo(
		
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
