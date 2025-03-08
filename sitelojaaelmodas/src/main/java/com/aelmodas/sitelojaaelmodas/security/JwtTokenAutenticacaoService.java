package com.aelmodas.sitelojaaelmodas.security;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.aelmodas.sitelojaaelmodas.AuthJWT.Usuario;
import com.aelmodas.sitelojaaelmodas.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtTokenAutenticacaoService {

	// Tempo de validade do token 10h
    private static final long EXPIRATION_TIME = 69120000;

    // Geração de chave segura de 512 bits
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(new byte[64]); 

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    private final UsuarioRepository usuarioRepository;

	public JwtTokenAutenticacaoService(UsuarioRepository usuarioRepository) {
	    this.usuarioRepository = usuarioRepository;
	}

	public void addAuthentication(HttpServletResponse response, String username) throws IOException {

		String JWT = Jwts.builder() // Chama o gerador de token
				.setSubject(username) // Adiciona o usuário
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Tempo de expiração
				.signWith(SECRET_KEY).compact(); 

		String token = TOKEN_PREFIX + " " + JWT; // Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiJ9.5
		response.addHeader(HEADER_STRING, token); // Adiciona no cabeçalho http de resposta

		// Escreve token como resposta no corpo do http
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            try {
            	String user = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

                if (user != null) {
                	Optional<Usuario> usuario = usuarioRepository.findByLogin(user);
                	
                	if (usuario.isPresent()) {
						return new UsernamePasswordAuthenticationToken(
								usuario.get().getLogin(), 
								usuario.get().getSenha(), 
								usuario.get().getAuthorities());
					}

                }
            } catch (Exception e) {
				return null;
			}
        }

		return null;
	}

}
