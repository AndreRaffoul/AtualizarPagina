package com.aelmodas.sitelojaaelmodas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;
import com.aelmodas.sitelojaaelmodas.service.EstoqueService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController // Alterado de @Controller para @RestController
@RequestMapping(value = "/estoque", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EstoqueController {
	
	@Autowired
	private EstoqueService service;

	// Método para buscar todos os produtos do estoque.
	@GetMapping("/buscarTodosNoEstoque")
	public ResponseEntity<List<EstoqueModel>> buscarTodosProdutos() {
		return ResponseEntity.ok(service.buscarTodosProdutos());
	}
	
	// Método para buscar um produto por id.
	@GetMapping("/buscarProdutoPorId/{id}")
	public ResponseEntity<EstoqueModel> buscarProdutoPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscarProdutoPorId(id));
	}
	
	// Método para salvar um produto no estoque.
	@PostMapping("/salvarProdutosEstoque")
	public ResponseEntity<EstoqueModel> salvarProdutoEstoque(@RequestBody EstoqueModel estoque) {
		return ResponseEntity.ok(service.salvarProdutoEstoque(estoque));
	}	
		
	// Método para deletar um produto por id.
	@GetMapping("/deletarProdutoPorId/{id}")
	public ResponseEntity<EstoqueModel> deletarProdutoPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.deletarProdutoPorId(id));
	}
	
	// Método para atualizar um produto por id.
	@PutMapping("/atualizarProdutoPorId/{id}")
	public ResponseEntity<EstoqueModel> atualizarProdutoPorId(@PathVariable Long id,
			@RequestBody EstoqueModel estoque) {
		EstoqueModel atualizado = service.atualizarProdutoPorId(id, estoque);
		return ResponseEntity.ok(atualizado);
	}
	

}
