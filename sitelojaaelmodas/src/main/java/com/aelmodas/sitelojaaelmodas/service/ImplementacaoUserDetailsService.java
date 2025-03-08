package com.aelmodas.sitelojaaelmodas.service;

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
		
		Usuario usuario = usuarioRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		return User.builder()
				.username(usuario.getLogin())
	            .password(usuario.getSenha())
	            .authorities(usuario.getAuthorities()) // Certifique-se de que `getAuthorities()` retorna uma coleção válida
	            .build();
	}

}
