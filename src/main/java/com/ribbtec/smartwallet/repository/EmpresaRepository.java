package com.ribbtec.smartwallet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ribbtec.smartwallet.entity.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>{

}
