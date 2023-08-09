package com.ribbtec.smartwallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ribbtec.smartwallet.dto.AtualizacaoEmpresaDTO;
import com.ribbtec.smartwallet.dto.CadastroEmpresaDTO;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "empresas")
@JsonInclude(Include.NON_NULL)
public class Empresa {

	@Id
	private String id;
	private String descricao;
	private String cnpj;
	private Boolean ativo;

	public Empresa(CadastroEmpresaDTO dados) {
		this.descricao = dados.descricao();
		this.cnpj = dados.cnpj();
		this.ativo = true;
	}

	public void atualizarDados(@Valid AtualizacaoEmpresaDTO dados) {
		
		if (dados.descricao() != null) {
			this.descricao = dados.descricao();
		}
		
		if (dados.cnpj() != null) {
			this.cnpj = dados.cnpj();
		}
	}

	public void desativar() {
		this.ativo = false;
	}
}
