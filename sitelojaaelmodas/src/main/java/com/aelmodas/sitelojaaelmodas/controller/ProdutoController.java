package com.aelmodas.sitelojaaelmodas.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelmodas.sitelojaaelmodas.model.ProdutoModel;
import com.aelmodas.sitelojaaelmodas.service.ProdutoService;

@RestController
@RequestMapping(value = "/produto", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController {
			
	@Autowired
	private ProdutoService service;
	
	// Método para deletar o produto do banco de dados por id
	@DeleteMapping("/deletarProdutoPorId/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<Map<String, String>> deletarProdutoPorId(@PathVariable Long id) {
	    Map<String, String> response = new HashMap<>();
	    try {
	        service.deletarProdutoPorId(id);
	        response.put("message", "Produto com ID " + id + " deletado com sucesso.");
	        return ResponseEntity.ok(response);
	    } catch (RuntimeException e) {
	        response.put("error", "Erro: Produto com ID " + id + " não encontrado.");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    } catch (Exception e) {
	        response.put("error", "Erro interno ao deletar o produto: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	
	// Método para atualizar o produto no banco de dados obedecendo a estrutura do json
	@PutMapping("/atualizarProdutoPorId/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<ProdutoModel> atualizarProdutoPorId( @PathVariable Long id, @Validated @RequestBody ProdutoModel produto) {
	    try {
	        ProdutoModel produtoAtualizado = service.atualizarProdutoPorId(id, produto);
	        return ResponseEntity.ok(produtoAtualizado);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	}	
	
	// Método para salvar o produto no banco de dados obedecendo a estrutura do json
	@PostMapping("/salvarProduto")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseEntity<?> salvarProduto(@Validated @RequestBody ProdutoModel produto) {
        try {
            ProdutoModel novoProduto = service.salvarProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);	
        } 
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar produto: " + e.getMessage());
        }
     }
	
	
	// Método para buscar produtos por id
	@GetMapping("/buscarProdutoPorId/{id}")
	public ResponseEntity<?> buscarProdutoPorId(@PathVariable("id") Long id) {
	    try {
	        ProdutoModel produto = service.buscarProdutoPorId(id);
	        return ResponseEntity.ok(produto);
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("Erro: Produto com ID " + id + " não encontrado.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Erro interno ao buscar o produto: " + e.getMessage());
	    }
	}
	
	// Método para buscar todos os produtos
	@GetMapping("/buscarTodosOsProdutos")
	public ResponseEntity<List<ProdutoModel>> buscarTodosOsProdutos() {
		LinkedList<ProdutoModel> produtos = new LinkedList<>(service.buscarTodosOsProduto());
		return ResponseEntity.ok(produtos);
	}

}
