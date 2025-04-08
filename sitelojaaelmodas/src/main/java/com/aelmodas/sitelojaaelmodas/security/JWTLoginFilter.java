package com.aelmodas.sitelojaaelmodas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.aelmodas.sitelojaaelmodas.AuthJWT.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private final JwtTokenAutenticacaoService jwtTokenAutenticacaoService;

    public JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, 
                          JwtTokenAutenticacaoService jwtTokenAutenticacaoService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
        
        this.jwtTokenAutenticacaoService = jwtTokenAutenticacaoService;     
        
    }

	@Override // Retorna o usuário ao tentar autenticar
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		System.out.println("🚀 Tentando autenticação com JWTLoginFilter... LOGG_01");
		
		Usuario user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(user.getLogin(), user.getSenha(), Collections.emptyList()));
	}

	@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
                                            FilterChain chain, Authentication authResult) 
            throws IOException, ServletException {
		
		System.out.println("🚀 Tentando autenticação com JWTLoginFilter... LOGG_02");
		
        jwtTokenAutenticacaoService.addAuthentication(response, authResult.getName());
    }

}
