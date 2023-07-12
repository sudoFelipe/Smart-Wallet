package com.ribbtec.smartwallet.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ribbtec.smartwallet.dto.AtualizacaoAtivoDTO;
import com.ribbtec.smartwallet.dto.AtualizacaoEmpresaDTO;
import com.ribbtec.smartwallet.dto.AtualizacaoTipoAtivoDTO;
import com.ribbtec.smartwallet.dto.CadastroAtivoDTO;

import jakarta.validation.Valid;
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
	private boolean ativo;
	
	public Ativo(CadastroAtivoDTO dados) {
		this.descricao = dados.descricao();
		this.ticker = dados.ticker();
		this.ativo = true;
		incluirComplementoEntidades(dados);
	}

	private void incluirComplementoEntidades(CadastroAtivoDTO dados) {
		this.instituicao = new Empresa(dados.instituicao());
		this.instituicao.setId(dados.instituicao().id());
		
		this.tipoAtivo = new TipoAtivo(dados.tipoAtivo());
		this.tipoAtivo.setId(dados.tipoAtivo().id());
	}

	public void atulizarDados(@Valid AtualizacaoAtivoDTO dados) {
		
		if (dados.descricao() != null) {
			this.descricao = dados.descricao();
		}
		
		if (dados.tipoAtivo() != null) {
			this.tipoAtivo.atualizarDados(
					new AtualizacaoTipoAtivoDTO(
							dados.tipoAtivo().getId(), 
							dados.tipoAtivo().getDescricao(), 
							dados.tipoAtivo().getSigla()));
		}
		
		if (dados.ticker() != null) {
			this.ticker = dados.ticker();
		}
		
		if (dados.instituicao() != null) {
			this.instituicao.atualizarDados(
					new AtualizacaoEmpresaDTO(
							dados.instituicao().getId(), 
							dados.instituicao().getDescricao(), 
							dados.instituicao().getCnpj()));
		}
	}

	public void desativar() {
		this.ativo = false;
	}
}
