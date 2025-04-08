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
import jakarta.servlet.http.HttpServletResponse;



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

	//@Override
    protected void doFilterInternal(HttpServletRequest request, 
    		HttpServletResponse response, 
    		FilterChain filterChain)
			throws ServletException, IOException {

    	System.out.println("[ FILTRO ] Interceptando requisição: " + request.getRequestURI());
    	
    	// Verifica se já existe uma autenticação
    	if ( SecurityContextHolder.getContext().getAuthentication() == null ) {
    		
    		Authentication authentication = jwtTokenAutenticacaoService.getAuthentication(request);
    		
    		if ( authentication != null ) {

    			System.out.println("[ FILTRO ] Autenticação encontrada: " + authentication.getName());
    			
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				System.out.println( " [ FILTRO ] Token inválido ou usuario não encontrado." );
			}
    		
    	} else {
			System.out.println( " [ FILTRO ] Autenticação já existe: " + SecurityContextHolder.getContext().getAuthentication().getName() );
		}
		
		// Continua o fluxo da requisição
		filterChain.doFilter(request, response);   	
    	
	}
	
}
