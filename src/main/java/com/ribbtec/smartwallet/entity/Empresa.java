package com.ribbtec.smartwallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ribbtec.smartwallet.record.InfoEmpresa;

import lombok.Data;

@Data
@Document(collection = "empresas")
public class Empresa {

	@Id
	private String id;
	private String descricao;
	private String cnpj;

	public Empresa(InfoEmpresa dados) {
		this.descricao = dados.descricao();
		this.cnpj = dados.cnpj();
	}
}
