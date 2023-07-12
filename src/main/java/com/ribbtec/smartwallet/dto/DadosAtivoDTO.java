package com.ribbtec.smartwallet.dto;

import com.ribbtec.smartwallet.entity.Ativo;
import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.entity.TipoAtivo;

public record DadosAtivoDTO(String id, String descricao, TipoAtivo tipoAtivo, String ticker, Empresa instituicao, boolean ativo) {

	public DadosAtivoDTO(Ativo ativo) {
		this(ativo.getId(), ativo.getDescricao(), ativo.getTipoAtivo(), ativo.getTicker(), ativo.getInstituicao(), ativo.isAtivo());
	}
}
