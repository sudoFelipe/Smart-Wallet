package com.ribbtec.smartwallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ribbtec.smartwallet.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	/*
	 * IMPLEMENTAÇÃO DA INTERFACE QUE REPRESENTARÁ O SERVIÇO DE AUTENTICAÇÃO
	 * Interface UserDetailsService
	 */
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return repository.findByLogin(username);
	}

}
