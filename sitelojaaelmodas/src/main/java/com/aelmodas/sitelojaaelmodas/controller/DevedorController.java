package com.aelmodas.sitelojaaelmodas.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.aelmodas.sitelojaaelmodas.model.DevedorModel;
import com.aelmodas.sitelojaaelmodas.service.DevedorService;

@RestController
@RequestMapping(value = "/devedor", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class DevedorController {
	
	@Autowired
	private DevedorService service;
	
	@GetMapping("/buscarTodosDevedores")
	public ResponseEntity<List<DevedorModel>> buscarTodosDevedores() {
	    LinkedList<DevedorModel> devedores = new LinkedList<>(service.buscarTodosDevedores());
	    return ResponseEntity.ok(devedores);
	}
	
	@GetMapping("/buscarDevedorPorId/{id}")
	public ResponseEntity<DevedorModel> buscarDevedorPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscarDevedorPorId(id));
	}
	
	@PostMapping("/salvarDevedor")
	public ResponseEntity<?> salvarDevedor(@RequestBody DevedorModel devedor) {
	    try {
	        DevedorModel novoDevedor = service.salvarDevedor(devedor);
	        return ResponseEntity.ok(novoDevedor);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
	            Map.of("erro", "Erro ao salvar devedor", "detalhe", e.getMessage())
	        );
	    }
	}
	
	@PutMapping("/atualizarDevedor/{id}")
	public ResponseEntity<DevedorModel> atualizarDevedor(@PathVariable Long id, @RequestBody DevedorModel devedor) {
	    DevedorModel devedorAtualizado = service.atualizarDevedorPorID(id, devedor);
	    return devedorAtualizado != null ? ResponseEntity.ok(devedorAtualizado) : ResponseEntity.notFound().build();
	}
	
	// Método para deletar um devedor.
	@DeleteMapping("/deletarDevedor/{id}")
	public ResponseEntity<Object> deletarDevedor(@PathVariable Long id) {
		boolean devedorModel = service.deletarDevedorPorID(id);
		if (!devedorModel) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}

}




