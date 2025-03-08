package com.aelmodas.sitelojaaelmodas.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtApiAutenticacaoFilter extends GenericFilterBean {
	
	 private final JwtTokenAutenticacaoService jwtTokenAutenticacaoService;

	    // Injetando JwtTokenAutenticacaoService via construtor
	    public JwtApiAutenticacaoFilter(JwtTokenAutenticacaoService jwtTokenAutenticacaoService) {
	        this.jwtTokenAutenticacaoService = jwtTokenAutenticacaoService;
	    }

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Obtém a autenticação do JWT
        Authentication authentication = jwtTokenAutenticacaoService.getAuthentication((HttpServletRequest) request);

        // Configura a autenticação no contexto do Spring Security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continua o fluxo da requisição
        chain.doFilter(request, response);
    }

}
