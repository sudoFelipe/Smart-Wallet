package com.ribbtec.smartwallet.dto;

import com.ribbtec.smartwallet.entity.Empresa;

import jakarta.validation.constraints.NotBlank;

public record DadosEmpresaDTO(
		
		@NotBlank
		String id, 
		
		@NotBlank
		String descricao, 
		
		@NotBlank
		String cnpj, 
		
		boolean ativo) {

	public DadosEmpresaDTO(Empresa empresa) {
		this(empresa.getId(), empresa.getDescricao(), empresa.getCnpj(), empresa.isAtivo());
	}
}
