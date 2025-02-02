package com.aelmodas.sitelojaaelmodas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aelmodas.sitelojaaelmodas.model.FornecedorModel;

@Repository
public interface FornecedorModelRepository extends JpaRepository<FornecedorModel, Long>  {

}
