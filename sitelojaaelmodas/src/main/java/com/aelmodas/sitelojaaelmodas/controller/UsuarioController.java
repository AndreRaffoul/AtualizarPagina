package com.aelmodas.sitelojaaelmodas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelmodas.sitelojaaelmodas.AuthJWT.Usuario;
import com.aelmodas.sitelojaaelmodas.service.UsuarioService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping(value = "/usuario", produces = "application/json")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PutMapping("/atualizarPorId/{id}")
	public ResponseEntity<Usuario> atualizarPorId(@PathVariable  Long id, @RequestBody Usuario usuario) {
		
		Usuario usuarioExistente = service.atualizarUsuarioPorID(id, usuario);
		
		if (usuarioExistente == null) {
			
            return ResponseEntity.notFound().build();
		}
		
        return ResponseEntity.ok(usuarioExistente);
	}

	@PostMapping("/registrar")
	@PreAuthorize("permitAll()") // PERMITE O ACESSO SEM AUTENTICAÇÃO
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
		
		// Criptografando a senha do usuário
		
		String senhaCripto = new BCryptPasswordEncoder().encode(usuario.getSenha());
		
		usuario.setSenha(senhaCripto);
		
		Usuario usuarioExistente = service.salvarUsuario(usuario);
        
		return ResponseEntity.status(201).body(usuarioExistente);
	}

	@DeleteMapping("/deletarPorId/{id}")
	public ResponseEntity<Usuario> deletarPorId(@PathVariable Long id) {
		
		Usuario usuario = service.deletarPorId(id);
		
		if (usuario == null) {
			
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		
		Usuario usuario = service.buscarPorId(id);
		
		if (usuario == null) {
			
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
		
	}

	@GetMapping("/buscarTodos")
	public List<Usuario> buscarTodos() {
		
		return service.buscarTodos();
	}

}
