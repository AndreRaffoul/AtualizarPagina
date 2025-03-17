package com.aelmodas.sitelojaaelmodas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.aelmodas.sitelojaaelmodas.service.ImplementacaoUserDetailsService;

import jakarta.servlet.Filter;

@Configuration
public class WebSecurityConfig { 

	private final ImplementacaoUserDetailsService implementacaoUserDetailsService;
    private final JwtTokenAutenticacaoService jwtTokenAutenticacaoService;
    private final AuthenticationConfiguration authenticationConfiguration;

    public WebSecurityConfig(ImplementacaoUserDetailsService implementacaoUserDetailsService, 
                             JwtTokenAutenticacaoService jwtTokenAutenticacaoService,
                             AuthenticationConfiguration authenticationConfiguration) {
        this.implementacaoUserDetailsService = implementacaoUserDetailsService;
        this.jwtTokenAutenticacaoService = jwtTokenAutenticacaoService;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    /* Configuração de autenticação */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .cors().and()
        .csrf(csrf -> csrf.disable())        
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permite requisições OPTIONS (CORS)
            .requestMatchers("/usuario/login", "/usuario/registrar").permitAll() // Libera o login sem autenticação
            .requestMatchers("/usuario/**", "/produto/**", "/fornecedoresCadas/**", "/estoque/**", "/devedores/**").authenticated()
            .anyRequest().authenticated())
        .logout(logout -> logout.logoutSuccessUrl("/usuario/login"))
        .addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }
    
    @Bean /* Configuração de autenticação */
    JWTLoginFilter jwtLoginFilter() throws Exception {    	
    	// Retonar o JWTLoginFilter com o caminho de login, o gerenciador de autenticação e o serviço de autenticação JWT
        return new JWTLoginFilter("/login", authenticationManager(authenticationConfiguration), jwtTokenAutenticacaoService);
    }
    
    @Bean /* Configuração de autenticação */
    JwtApiAutenticacaoFilter jwtApiAutenticacaoFilter() {    	
    	// Retonar o JwtApiAutenticacaoFilter com o serviço de autenticação JWT
        return new JwtApiAutenticacaoFilter(jwtTokenAutenticacaoService);
    }

    /* Configuração de autenticação */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	
    	// Retonar o gerenciador de autenticação
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean /* Configuração de autenticação */
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean /* Configuração de autenticação */
    AuthenticationProvider authenticationProvider() {
    	
    	// Retonar o provedor de autenticação
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        
        // Configura o provedor de autenticação com o serviço de detalhes do usuário
        authProvider.setUserDetailsService(implementacaoUserDetailsService);
        
        // Configura o provedor de autenticação com o codificador de senha
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return authProvider;
    }

}
