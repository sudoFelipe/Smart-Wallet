package com.ribbtec.smartwallet.record;

import com.ribbtec.smartwallet.entity.Empresa;
import com.ribbtec.smartwallet.entity.TipoAtivo;

public record InfoAtivo(
String id, String descricao, /* TipoAtivo tipoAtivo, */
String ticker /*, Empresa instituicao */) {

}
