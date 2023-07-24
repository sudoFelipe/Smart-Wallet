package com.ribbtec.smartwallet.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastroEmpresaDTO(
		
		
		String id, 
		
		//	CONFIG. RESPOSTA DE ERROS
//		@NotBlank(message = "mensagem de erro")
		@NotBlank(message = "{descricao.obrigatorio}")	//	MENSAGEM EXTRAÍDA DO ARQUIVO ValidationMessages.properties
		String descricao, 
		
		@NotBlank(message = "{cnpj.obrigatorio}")
		String cnpj) {

}
