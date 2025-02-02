package com.aelmodas.sitelojaaelmodas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelmodas.sitelojaaelmodas.model.FornecedorModel;
import com.aelmodas.sitelojaaelmodas.repository.FornecedorModelRepository;

@Service
public class FornecedorModelService {
	
	@Autowired
	private FornecedorModelRepository fornecedorRepository;
	
	// Salvar fornecedor
	public FornecedorModel salvarFornecedor(FornecedorModel fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}
	
	// Atualizar fornecedor
	public FornecedorModel atualizarFornecedorPorID(Long id, FornecedorModel fornecedor) {
        return fornecedorRepository.findById(id).map(existingFornecedor -> {
        	existingFornecedor.setId(fornecedor.getId());
            existingFornecedor.setNomeFornecedor(fornecedor.getNomeFornecedor());
            existingFornecedor.setEmail(fornecedor.getEmail());
            existingFornecedor.setTelefone(fornecedor.getTelefone());
            existingFornecedor.setEndereco(fornecedor.getEndereco());
            existingFornecedor.setCidade(fornecedor.getCidade());
            existingFornecedor.setEstado(fornecedor.getEstado());
            existingFornecedor.setCep(fornecedor.getCep());
            existingFornecedor.setCnpj(fornecedor.getCnpj());
            // Salva o fornecedor atualizado
            return fornecedorRepository.save(existingFornecedor);
        }).orElse(null); // Retorna null se o fornecedor não for encontrado
	}
	
	// Deletar fornecedor
	public boolean deletarFornecedorPorID(Long id) {
		if (fornecedorRepository.existsById(id)) {
			fornecedorRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	// Buscar fornecedor por id
	public FornecedorModel buscarFornecedorPorId(Long id) {
		return fornecedorRepository.findById(id).orElse(null);
		 
	}
	
	// Buscar todos os fornecedores
	public List<FornecedorModel> buscarTodosFornecedores() {
		return fornecedorRepository.findAll();
	}

}
