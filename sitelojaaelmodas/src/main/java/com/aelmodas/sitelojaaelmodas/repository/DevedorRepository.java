package com.aelmodas.sitelojaaelmodas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aelmodas.sitelojaaelmodas.model.DevedorModel;

@Repository
public interface DevedorRepository extends  JpaRepository<DevedorModel, Long> {

}
