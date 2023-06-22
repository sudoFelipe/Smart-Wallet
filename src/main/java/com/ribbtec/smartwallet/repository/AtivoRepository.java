package com.ribbtec.smartwallet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ribbtec.smartwallet.entity.Ativo;

@Repository
public interface AtivoRepository extends MongoRepository<Ativo, String> {

}
