package com.aelmodas.sitelojaaelmodas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelmodas.sitelojaaelmodas.model.DevedorModel;
import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;
import com.aelmodas.sitelojaaelmodas.model.EstoqueTecidoJoin;
import com.aelmodas.sitelojaaelmodas.model.TecidoModel;
import com.aelmodas.sitelojaaelmodas.repository.DevedorRepository;
import com.aelmodas.sitelojaaelmodas.repository.EstoqueRepository;
import com.aelmodas.sitelojaaelmodas.repository.TecidoRepository;

import jakarta.transaction.Transactional;

@Service
public class DevedorService {
	
		@Autowired
		private DevedorRepository devedorRepository;
	
		@Autowired
	    private EstoqueRepository estoqueRepository;
	    
	    @Autowired
	    private TecidoRepository tecidoRepository;
	
	    @Transactional
	    public DevedorModel salvarDevedor(DevedorModel devedor) {
	        if (devedor.getEstoqueTecidoList() != null) {
	            for (EstoqueTecidoJoin estoqueTecido : devedor.getEstoqueTecidoList()) {

	                if (estoqueTecido.getEstoqueModel() == null || estoqueTecido.getEstoqueModel().getId() == null) {
	                    throw new RuntimeException("Erro: EstoqueModel está ausente ou sem ID no JSON.");
	                }

	                if (estoqueTecido.getTecidoModel() == null || estoqueTecido.getTecidoModel().getId() == null) {
	                    throw new RuntimeException("Erro: TecidoModel está ausente ou sem ID no JSON.");
	                }

	                // Busca ou cria EstoqueModel
	                EstoqueModel estoqueModel = estoqueRepository.findById(estoqueTecido.getEstoqueModel().getId())
	                    .orElseGet(() -> {
	                        EstoqueModel novoEstoque = new EstoqueModel();
	                        novoEstoque.setId(estoqueTecido.getEstoqueModel().getId());
	                        novoEstoque.setProduto("Produto Padrão");
	                        return estoqueRepository.save(novoEstoque);
	                    });

	                estoqueTecido.setEstoqueModel(estoqueModel);

	                // Busca ou cria TecidoModel
	                TecidoModel tecidoModel = tecidoRepository.findById(estoqueTecido.getTecidoModel().getId())
	                    .orElseGet(() -> {
	                        TecidoModel novoTecido = new TecidoModel();
	                        novoTecido.setId(estoqueTecido.getTecidoModel().getId());
	                        novoTecido.setNome("Tecido Padrão");
	                        return tecidoRepository.save(novoTecido);
	                    });

	                estoqueTecido.setTecidoModel(tecidoModel);

	                estoqueTecido.setDevedorModel(devedor);
	            }
	        }
	        return devedorRepository.save(devedor);
	    }

	    @Transactional
	    public DevedorModel atualizarDevedorPorID(Long id, DevedorModel devedor) {
	        return devedorRepository.findById(id).map(existingDevedor -> {
	            // Atualiza os campos básicos
	            existingDevedor.setNomeDevedor(devedor.getNomeDevedor());
	            existingDevedor.setEmail(devedor.getEmail());
	            existingDevedor.setTelefone(devedor.getTelefone());
	            existingDevedor.setEndereco(devedor.getEndereco());
	            existingDevedor.setCpf(devedor.getCpf());
	            existingDevedor.setDivida(devedor.getDivida());
	            existingDevedor.setDataDivida(devedor.getDataDivida());
	            existingDevedor.setDataVencimento(devedor.getDataVencimento());
	            existingDevedor.setStatusDivida(devedor.getStatusDivida());
	            existingDevedor.setObservacoes(devedor.getObservacoes());
	            existingDevedor.setPreco(devedor.getPreco());
	            existingDevedor.setQuantidade(devedor.getQuantidade());

	            if (devedor.getEstoqueTecidoList() != null) {
	                existingDevedor.getEstoqueTecidoList().clear(); // Remove os antigos
	                for (EstoqueTecidoJoin estoqueTecido : devedor.getEstoqueTecidoList()) {
	                    
	                    // ✅ Certifica-se de que EstoqueModel e TecidoModel existem antes de associar
	                    EstoqueModel estoqueModel = estoqueRepository.findById(estoqueTecido.getEstoqueModel().getId())
	                        .orElseThrow(() -> new RuntimeException(
	                            "Erro: EstoqueModel ID " + estoqueTecido.getEstoqueModel().getId() + " não encontrado no banco."
	                        ));

	                    TecidoModel tecidoModel = tecidoRepository.findById(estoqueTecido.getTecidoModel().getId())
	                        .orElseThrow(() -> new RuntimeException(
	                            "Erro: TecidoModel ID " + estoqueTecido.getTecidoModel().getId() + " não encontrado no banco."
	                        ));
	                    
	                    estoqueTecido.setEstoqueModel(estoqueModel);
	                    estoqueTecido.setTecidoModel(tecidoModel);
	                    estoqueTecido.setDevedorModel(existingDevedor);
	                    
	                    existingDevedor.getEstoqueTecidoList().add(estoqueTecido);
	                }
	            }

	            return devedorRepository.save(existingDevedor);
	        }).orElse(null);
	    }

	
	// Deletar devedor
	public boolean deletarDevedorPorID(Long id) {
		if (devedorRepository.existsById(id)) {
			devedorRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	// Buscar devedor por id
	public DevedorModel buscarDevedorPorId(Long id) {
		return devedorRepository.findById(id).orElse(null);
	}
	
	// Buscar todos os devedores
	public List<DevedorModel> buscarTodosDevedores() {
		return devedorRepository.findAll();
	}

}
