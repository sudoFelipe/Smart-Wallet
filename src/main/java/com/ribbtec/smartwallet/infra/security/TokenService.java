package com.ribbtec.smartwallet.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.ribbtec.smartwallet.entity.Usuario;

@Service
public class TokenService {

	public String gerarToken(Usuario usuario) {
		try {
		    Algorithm algoritmo = Algorithm.HMAC256("12345678");
		    return JWT.create()
		    		.withIssuer("API-SMARTWALLET")
		    		.withSubject(usuario.getLogin())	//	SUJEITO OU INDIVÍDUO QUE ESTÁ SOLICITANDO O TOKEN
		    		.withExpiresAt(dataExpiracao())		//	COLOCANDO UMA DATA DE EXPIRAÇÃO NO TOKEN
		    		.withClaim("id", usuario.getId())	//	INFORMAÇÕES QUE SERÃO PASSADAS VIA TOKEN (PODE SER USADO VÁRIAS VEZES)
		    		.sign(algoritmo);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Erro ao gerar token JWT", exception);
		}
	}

	private Instant dataExpiracao() {
		// INDICA QUE O TOKEN IRÁ EXPIRAR EM 2 HORAS DE ACORDO COM O HORÁRIO LOCAL
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
