package com.ribbtec.smartwallet.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ribbtec.smartwallet.entity.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>{

	List<Empresa> findByAtivoTrue(Pageable paginacao);

}
