package com.ribbtec.smartwallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ribbtec.smartwallet.dto.CadastroEmpresaDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "empresas")
public class Empresa {

	@Id
	private String id;
	private String descricao;
	private String cnpj;

	public Empresa(CadastroEmpresaDTO dados) {
		this.descricao = dados.descricao();
		this.cnpj = dados.cnpj();
	}
}
