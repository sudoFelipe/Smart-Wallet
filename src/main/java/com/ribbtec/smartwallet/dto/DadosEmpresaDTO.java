package com.ribbtec.smartwallet.dto;

import com.ribbtec.smartwallet.entity.Empresa;

public record DadosEmpresaDTO(String id, String descricao, String cnpj) {

	public DadosEmpresaDTO(Empresa empresa) {
		this(empresa.getId(), empresa.getDescricao(), empresa.getCnpj());
	}
}
