package com.aelmodas.sitelojaaelmodas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aelmodas.sitelojaaelmodas.AuthJWT.Usuario;
import com.aelmodas.sitelojaaelmodas.repository.UsuarioRepository;

@Service
public class ImplementacaoUserDetailsService implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ImplementacaoUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(username);
        
    	Usuario usuario = usuarioOptional.orElseThrow(() ->
        new UsernameNotFoundException("Usuário não encontrado: " + username));

	    return User.builder()
	            .username(usuario.getLogin())
	            .password(usuario.getSenha())
	            .authorities(usuario.getAuthorities())
	            .build();
    }

}
