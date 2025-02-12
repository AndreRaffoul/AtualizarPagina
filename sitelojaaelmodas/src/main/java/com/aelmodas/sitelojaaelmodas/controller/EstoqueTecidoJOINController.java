package com.aelmodas.sitelojaaelmodas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelmodas.sitelojaaelmodas.model.EstoqueTecidoJoin;
import com.aelmodas.sitelojaaelmodas.model.FornecedorModel;
import com.aelmodas.sitelojaaelmodas.service.EstoqueTecidoJOINService;

@RestController
@RequestMapping("/estoque-tecido-join")
public class EstoqueTecidoJOINController {
	
	@Autowired
	private EstoqueTecidoJOINService estoqueTecidoJOINService;
	
	// 🔹 Método para salvar um único objeto
    @PostMapping(value = "/salvar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstoqueTecidoJoin> salvar(@RequestBody EstoqueTecidoJoin estoqueTecidoJoin) {
        EstoqueTecidoJoin salvo = estoqueTecidoJOINService.salvar(estoqueTecidoJoin);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // 🔹 Método para salvar uma lista de objetos
    @PostMapping(value = "/salvarTodos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstoqueTecidoJoin>> salvarTodos(@RequestBody List<EstoqueTecidoJoin> estoqueTecidoList) {
        List<EstoqueTecidoJoin> salvos = estoqueTecidoJOINService.salvarTodos(estoqueTecidoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvos);
    }
	
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<EstoqueTecidoJoin>> buscarTodos() {
        List<EstoqueTecidoJoin> lista = estoqueTecidoJOINService.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscarPorID/{id}")
    public ResponseEntity<EstoqueTecidoJoin> buscarPorId(@PathVariable Long id) {
        Optional<EstoqueTecidoJoin> estoqueTecido = estoqueTecidoJOINService.buscarPorId(id);
        return estoqueTecido.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("deletarPorID/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        estoqueTecidoJOINService.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/atualizarEstoquePorId/{id}")
	public ResponseEntity<EstoqueTecidoJoin> atualizarEstoquePorId(@PathVariable Long id,
			@RequestBody EstoqueTecidoJoin estoqueTecidoJoin) {
    	try {
            EstoqueTecidoJoin atualizado = estoqueTecidoJOINService.atualizarEstoquePorId(id, estoqueTecidoJoin);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
	}

}
