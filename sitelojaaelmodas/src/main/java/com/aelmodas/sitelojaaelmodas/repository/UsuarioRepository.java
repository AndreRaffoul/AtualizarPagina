package com.aelmodas.sitelojaaelmodas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aelmodas.sitelojaaelmodas.AuthJWT.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        
	@Query("SELECT u FROM Usuario u WHERE u.login = ?1")
	Optional<Usuario> findByLogin(String login);
    
    Usuario findByEmail(String email);
    
    @Query("SELECT u FROM Usuario u WHERE u.login = :login OR u.email = :email")
    Usuario buscarPorLoginOuEmail(@Param("login") String login, @Param("email") String email);

    void deleteById(Long id);

}
