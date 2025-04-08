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
	
	public FornecedorModel salvarFornecedor(FornecedorModel fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}
	
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
        }).orElse(null);
	}
	
	public boolean deletarFornecedorPorID(Long id) {
		if (fornecedorRepository.existsById(id)) {
			fornecedorRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public FornecedorModel buscarFornecedorPorId(Long id) {
		return fornecedorRepository.findById(id).orElse(null);
		 
	}
	
	public List<FornecedorModel> buscarTodosFornecedores() {
		
		System.out.println("🔍 [FornecedorModelService] Buscando todos os fornecedores..." );
		
		return fornecedorRepository.findAll();
	}

}
