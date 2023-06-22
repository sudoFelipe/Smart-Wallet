package com.ribbtec.smartwallet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	//	CONFIGURAÇÃO DE CORS (PERMITE QUE A APLICAÇÃO DE ORIGEM CONSIGA REALIZAR O ACESSO DE UMA ORIGEM DIFERENTE
	//	COM OS MÉTODOS DE REQUISIÇÃO INSERIDOS ABAIXO
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
