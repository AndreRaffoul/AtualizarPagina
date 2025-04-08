package com.aelmodas.sitelojaaelmodas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aelmodas.sitelojaaelmodas.service.ImplementacaoUserDetailsService;

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

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .cors().and()
        .csrf().disable()        
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/usuario/login", "/usuario/registrar").permitAll()
            .requestMatchers("/usuario/**", "/produto/**", "/fornecedor/**", "/estoque/**", "/devedores/**").authenticated()
            .anyRequest().authenticated())
        .logout(logout -> logout.logoutSuccessUrl("/usuario/login"))
        .addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }
    
    @Bean
    JWTLoginFilter jwtLoginFilter() throws Exception {
        return new JWTLoginFilter("/login", authenticationManager(authenticationConfiguration), jwtTokenAutenticacaoService);
    }
    
    @Bean
    JwtApiAutenticacaoFilter jwtApiAutenticacaoFilter() {    	
        return new JwtApiAutenticacaoFilter(jwtTokenAutenticacaoService);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(implementacaoUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return authProvider;
    }

}
