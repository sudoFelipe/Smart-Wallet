package com.ribbtec.smartwallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "ativos")
public class Ativo {

	@Id
	private String id;
	private String descricao;
	private TipoAtivo tipoAtivo;
	private String ticker;
	private Empresa instituicao;
}
