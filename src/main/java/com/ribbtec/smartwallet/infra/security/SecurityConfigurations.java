package com.ribbtec.smartwallet.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration		//	CLASSE DE CONFIGURAÇÃO
@EnableWebSecurity	//	PERSONALIZAÇÃO DAS CONFIGURAÇÕES DE SEGURANÇA
//	@EnableMethodSecurity(securedEnabled = true)	// PERMITIR ANOTAÇÃO DE SEGURANÇA NOS MÉTODOS (@Secure)
public class SecurityConfigurations {

	@Autowired
	SecurityFilter securityFilter;
	
	/*
	 * OBJETO DO SPRING USADO PARA CONFIGURAR A PARTE DE AUTENTICAÇÃO E AUTORIZAÇÃO
	 * SecurityFilterChain
	 */
	
	/*
	 * Bean SERVE PARA EXPORTAR UMA CLASSE PARA O SPRING, FAZENDO COM QUE ELE CONSIGA CARREGÁ-LA
	 * E REALIZE A SUA INJEÇÃO DE DEPENDÊNCIA EM OUTRAS CLASSES
	 * ===========================================================================================
	 * Bean EXPÕE O RETORNO DO MÉTODO (DEVOLVE UM OBJETO PARA O SPRING OU A POSSIBILIDADE DE INJEÇÃO DE 
	 * DEPENDÊNCIA EM OUTRO CONTROLLER OU CLASSE SPRING)
	 */
	 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http.csrf(csrf -> csrf.disable())	//	DESABILITANDO A PROTEÇÃO DO CSRF (O PRÓPRIO TOKEN QUE SERÁ GERADO JÁ NOS PROTEGE CONTRA ESSE TIPO DE ATAQUE)
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.POST, "/login").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/ativo").hasRole("ADMIN")	//	DEFININDO ROLE (PERMISSAO) PARA A AÇÃO DE REMOÇÃO
						.anyRequest().authenticated())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)	// DEFINE A ORDEM DE CHAMADA DOS FILTER's (Filter criado por nós e posteriormente o filtro padrão do Spring)
				.build();		//	MODIFICA O PROCESSO DE AUTENTICAÇÃO PADRÃO DO SPRING DE STATEFULL PARA STATELESS
	};
	
	/**
	 * CRIA UM OBJETO AUTHENTICATION MANAGER PARA SER USADO NO CONTROLLER DE AUTENTICAÇÃO
	 * @param configuration
	 * @return
	 * @throws Exception
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		//	ESSA CLASSE CRIARÁ UM OBJETO DO TIPO AUTHENTICATIONMANGAER, PARA QUE O SPRING POSSA ENCONTRÁ-LO
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
