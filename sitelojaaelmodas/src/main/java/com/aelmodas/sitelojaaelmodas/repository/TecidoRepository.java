package com.aelmodas.sitelojaaelmodas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aelmodas.sitelojaaelmodas.model.TecidoModel;

@Repository
public interface TecidoRepository extends JpaRepository<TecidoModel, Long> {

	Optional<TecidoModel> findByNome(String nome);

}
