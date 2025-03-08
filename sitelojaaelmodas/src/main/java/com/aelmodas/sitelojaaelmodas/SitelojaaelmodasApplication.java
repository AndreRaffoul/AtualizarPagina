package com.aelmodas.sitelojaaelmodas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.aelmodas.sitelojaaelmodas.model",
		"com.aelmodas.sitelojaaelmodas.controller", 
		"com.aelmodas.sitelojaaelmodas.repository",
		"com.aelmodas.sitelojaaelmodas.service",
		"com.aelmodas.sitelojaaelmodas.AuthJWT"})
@ComponentScan(basePackages = {"com.aelmodas.sitelojaaelmodas.*"})
@EnableJpaRepositories(basePackages = {"com.aelmodas.sitelojaaelmodas.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class SitelojaaelmodasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitelojaaelmodasApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("minhaSenhaSegura123"));
	}

}
