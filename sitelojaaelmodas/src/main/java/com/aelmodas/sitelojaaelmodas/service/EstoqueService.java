package com.aelmodas.sitelojaaelmodas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;
import com.aelmodas.sitelojaaelmodas.repository.EstoqueRepository;

@Service
public class EstoqueService {
	
	@Autowired
	private EstoqueRepository estoqueRepo;
	
	// Método para buscar todos os produtos do estoque.
	public List<EstoqueModel> buscarTodosProdutos() {
        return estoqueRepo.findAll();
	}
	
	// Método para salvar um produto no estoque.
	public EstoqueModel salvarProdutoEstoque(EstoqueModel estoque) {
		return estoqueRepo.save(estoque);
	}
	
	
	

}
