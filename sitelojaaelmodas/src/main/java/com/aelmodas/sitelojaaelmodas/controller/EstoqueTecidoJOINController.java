package com.aelmodas.sitelojaaelmodas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;
import com.aelmodas.sitelojaaelmodas.model.EstoqueTecido_JOIN;
import com.aelmodas.sitelojaaelmodas.service.EstoqueTecidoJOINService;

@RestController
@RequestMapping("/estoque-tecido-join")
public class EstoqueTecidoJOINController {
	
	@Autowired
	private EstoqueTecidoJOINService estoqueTecidoJOINService;
	
	@PostMapping("/salvar")
    public ResponseEntity<EstoqueModel> salvar(@RequestBody EstoqueModel estoqueModel) {
        // Delegar ao Service para salvar o EstoqueModel e os relacionamentos
        EstoqueModel salvo = estoqueTecidoJOINService.salvarComTecidos(estoqueModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
	
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<EstoqueTecido_JOIN>> buscarTodos() {
        List<EstoqueTecido_JOIN> lista = estoqueTecidoJOINService.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    // Consultar por ID
    @GetMapping("/buscarPorID/{id}")
    public ResponseEntity<EstoqueTecido_JOIN> buscarPorId(@PathVariable Long id) {
        Optional<EstoqueTecido_JOIN> estoqueTecido = estoqueTecidoJOINService.buscarPorId(id);
        if (estoqueTecido.isPresent()) {
            return ResponseEntity.ok(estoqueTecido.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Deletar por ID
    @DeleteMapping("deletarPorID/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
    	estoqueTecidoJOINService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
