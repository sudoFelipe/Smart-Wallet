package com.ribbtec.smartwallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ribbtec.smartwallet.record.InfoTipoAtivo;

import lombok.Data;

@Data
@Document(collection = "ativos-financeiros")
public class TipoAtivo {

	@Id
	private String id;
	private String descricao;
	private String sigla;

	public TipoAtivo(InfoTipoAtivo dados) {
		this.descricao = dados.descricao();
		this.sigla = dados.sigla();
	}
}
