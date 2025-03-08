package com.aelmodas.sitelojaaelmodas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;

@Repository	
public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {
            
}
