package com.aelmodas.sitelojaaelmodas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelmodas.sitelojaaelmodas.model.FornecedorModel;
import com.aelmodas.sitelojaaelmodas.service.FornecedorModelService;


@RestController
@RequestMapping("/fornecedor")
@CrossOrigin(origins = "http://localhost:4200")
public class FornecedorModelController {
	
	@Autowired
	private FornecedorModelService fornecedorService;
	
	@PostMapping("/salvar")
	public ResponseEntity<FornecedorModel> salvarFornecedor(
			@RequestBody FornecedorModel fornecedor) {
		FornecedorModel fornecedorModel = fornecedorService.salvarFornecedor(fornecedor);
		return ResponseEntity.ok(fornecedorModel);
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<FornecedorModel> atualizarFornecedor( 
			@PathVariable Long id, @RequestBody FornecedorModel fornecedor) {		
		FornecedorModel fornecedorModel = fornecedorService.atualizarFornecedorPorID(id, fornecedor);
		if (fornecedorModel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(fornecedorModel);		
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarFornecedor(@PathVariable Long id) {
		boolean fornecedorModel = fornecedorService.deletarFornecedorPorID(id);
		if (!fornecedorModel) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/buscarPorId/{id}")
	public ResponseEntity<FornecedorModel> buscarFornecedorPorId(@PathVariable Long id) {		
		FornecedorModel fornecedorModel = fornecedorService.buscarFornecedorPorId(id);
		if (fornecedorModel == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(fornecedorModel);
	}
	
	@GetMapping("/buscarTodos")
	public ResponseEntity<List<FornecedorModel>> buscarTodosFornecedores() {
		List<FornecedorModel> fornecedores = fornecedorService.buscarTodosFornecedores();
		return ResponseEntity.ok(fornecedores);
	}
	

}
