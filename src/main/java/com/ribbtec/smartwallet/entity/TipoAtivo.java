package com.ribbtec.smartwallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ribbtec.smartwallet.dto.AtualizacaoTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroTipoAtivoDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "ativos-financeiros")
public class TipoAtivo {

	@Id
	private String id;
	private String descricao;
	private String sigla;
	private boolean ativo;

	public TipoAtivo(CadastroTipoAtivoDTO dados) {
		this.descricao = dados.descricao();
		this.sigla = dados.sigla();
		this.ativo = true;
	}
	
	public void atualizarDados(AtualizacaoTipoAtivoDTO dados) {
		if (dados.descricao() != null) {
			this.descricao = dados.descricao();
		}
		
		if (dados.sigla() != null) {
			this.sigla = dados.sigla();
		}
	}

	public void desativar() {
		
		this.ativo = false;
	}
}
