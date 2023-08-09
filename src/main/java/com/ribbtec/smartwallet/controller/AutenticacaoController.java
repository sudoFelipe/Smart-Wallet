package com.ribbtec.smartwallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ribbtec.smartwallet.dto.DadosAutenticacao;
import com.ribbtec.smartwallet.entity.Usuario;
import com.ribbtec.smartwallet.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	//	CLASSE REPONSÁVEL POR GERENCIAR AS AUTENTICAÇÕES NO SPRING
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity acessarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		
		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication = manager.authenticate(token);
		
		//	RETORNANDO O TOKEN JWT
		return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
	}
}
