package com.ribbtec.smartwallet.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ribbtec.smartwallet.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component	//	USADO PARA QUE O SPRING CARREGUE UMA CLASSE/COMPONENTE GENÉRICO
public class SecurityFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	//	CHAMADO NO INÍCIO DA REQUISIÇÃO, CONTENDO A REGRA PARA SEGUIR O FLUXO
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//	RECUPERA O TOKEN
		var tokenJWT = recuperarToken(request);
		
		//	VERIFICA SE O TOKEN FOI GERADO
		if (tokenJWT != null) {
			
			//	VALIDAÇÃO DO TOKEN COM O SUBJECT
			var subject = tokenService.getSubject(tokenJWT);
			
			//	RECUPERANDO O USUÁRIO PELO LOGIN
			var usuario = usuarioRepository.findByLogin(subject);
			
			//	CLASSE DTO DO SPRING COM A AUTENTICAÇÃO DO TOKEN
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			
			//	FORÇA A AUTENTICAÇÃO DO USUÁRIO RECUPERADO
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		//	FILTER CHAIN REPRESENTA A CADEIA DE FILTROS DA APLICAÇÃO
		//	CHAMA O PRÓXIMO FILTRO
		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		
		var authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader != null) {
			
			return authorizationHeader.replace("Bearer ", "");
		}
		
		return null;
	}

}
