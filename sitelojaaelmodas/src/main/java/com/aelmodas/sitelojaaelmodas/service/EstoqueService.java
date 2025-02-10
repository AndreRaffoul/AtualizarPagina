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
	
	// Método para buscar um produto por id.
	public EstoqueModel buscarProdutoPorId(Long id) {
		return estoqueRepo.findById(id).orElse(null);
	}
	
	// Método para deletar um produto por id.
	public EstoqueModel deletarProdutoPorId(Long id) {
		return estoqueRepo.findById(id).map(estoque -> {
			estoqueRepo.delete(estoque);
			return estoque;
		}).orElse(null);
	}
	
	// Método para atualizar um produto por id.
	public EstoqueModel atualizarProdutoPorId(Long id, EstoqueModel estoque) {
		return estoqueRepo.findById(id).map(existingEstoque -> {
			existingEstoque.setId(estoque.getId());
			existingEstoque.setProduto(estoque.getProduto());
			existingEstoque.setPluzSize(estoque.getPluzSize());
			existingEstoque.setValorCompra(estoque.getValorCompra());
			existingEstoque.setValorRevenda(estoque.getValorRevenda());
			existingEstoque.setDataCompra(estoque.getDataCompra());
			existingEstoque.setObservacoes(estoque.getObservacoes());
			return estoqueRepo.save(existingEstoque);
		}).orElse(null);
	}

}
