package com.aelmodas.sitelojaaelmodas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aelmodas.sitelojaaelmodas.model.EstoqueTecidoJoin;

@Repository
public interface EstoqueTecidoJOINRepository  extends JpaRepository<EstoqueTecidoJoin, Long>{

}


