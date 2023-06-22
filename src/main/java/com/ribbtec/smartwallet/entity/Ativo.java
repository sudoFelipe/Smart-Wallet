package com.ribbtec.smartwallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "ativos")
@Data
public class Ativo {

	@Id
	private String id;
	private String descricao;
	private TipoAtivo tipoAtivo;
	private String ticker;
	private Empresa instituicao;
}
