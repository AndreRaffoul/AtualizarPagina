package com.aelmodas.sitelojaaelmodas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;

public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {
        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1", nativeQuery = true)
//        List<EstoqueModel> findByTecido(Long tecido_id);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
//        
//        @Query(value = "SELECT * FROM tb_estoque WHERE tecido_id = ?1 AND tamanho = ?2", nativeQuery = true)
//        EstoqueModel findByTecidoAndTamanho(Long tecido_id, String tamanho);
        
}
