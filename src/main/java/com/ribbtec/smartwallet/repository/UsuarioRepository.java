package com.ribbtec.smartwallet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.ribbtec.smartwallet.entity.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

	UserDetails findByLogin(String login);

}
