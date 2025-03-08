package com.aelmodas.sitelojaaelmodas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;
import com.aelmodas.sitelojaaelmodas.model.FornecedorModel;
import com.aelmodas.sitelojaaelmodas.model.ProdutoModel;
import com.aelmodas.sitelojaaelmodas.model.TecidoModel;
import com.aelmodas.sitelojaaelmodas.repository.EstoqueRepository;
import com.aelmodas.sitelojaaelmodas.repository.FornecedorModelRepository;
import com.aelmodas.sitelojaaelmodas.repository.ProdutoRepository;
import com.aelmodas.sitelojaaelmodas.repository.TecidoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private TecidoRepository tecidoRepository;
	
	@Autowired
	private FornecedorModelRepository fornecedorRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	
	@Transactional
	public void deletarProdutoPorId(Long id) {
	    if (id == null) {
	        throw new IllegalArgumentException("O ID do produto não pode ser nulo.");
	    }
	    
	    ProdutoModel produto = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado."));

	    repository.delete(produto);
	}
	
	@Transactional
    public ProdutoModel atualizarProdutoPorId(Long id, ProdutoModel produtoAtualizado) {
        // Busca o produto existente ou lança exceção
        ProdutoModel produtoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado."));

        // Copia as propriedades simples (exceto id e associações)
        BeanUtils.copyProperties(produtoAtualizado, produtoExistente, "id", "tecidoModelList", "estoqueModelList", "forneceModelList");

        if (produtoAtualizado.getTecidoModelList() != null) {
            List<TecidoModel> novosTecidos = new ArrayList<>();
            for (TecidoModel tecido : produtoAtualizado.getTecidoModelList()) {
                TecidoModel tecidoModel = tecidoRepository.findById(tecido.getId())
                        .orElseThrow(() -> new RuntimeException("Erro: TecidoModel ID " + tecido.getId() + " não encontrado."));
                novosTecidos.add(tecidoModel);
            }
            produtoExistente.setTecidoModelList(novosTecidos);
        }

        if (produtoAtualizado.getForneceModelList() != null) {
            List<FornecedorModel> novosFornecedores = new ArrayList<>();
            for (FornecedorModel fornecedor : produtoAtualizado.getForneceModelList()) {
                FornecedorModel fornecedorModel = fornecedorRepository.findById(fornecedor.getId())
                        .orElseThrow(() -> new RuntimeException("Erro: FornecedorModel ID " + fornecedor.getId() + " não encontrado."));
                novosFornecedores.add(fornecedorModel);
            }
            produtoExistente.setForneceModelList(novosFornecedores);
        }

        if (produtoAtualizado.getEstoqueModelList() != null) {
            List<EstoqueModel> novosEstoques = new ArrayList<>();
            for (EstoqueModel estoque : produtoAtualizado.getEstoqueModelList()) {
                EstoqueModel estoqueModel = estoqueRepository.findById(estoque.getId())
                        .orElseThrow(() -> new RuntimeException("Erro: EstoqueModel ID " + estoque.getId() + " não encontrado."));
                novosEstoques.add(estoqueModel);
            }
            produtoExistente.setEstoqueModelList(novosEstoques);
        }

        return repository.save(produtoExistente);
    }
	
	@Transactional
	public ProdutoModel salvarProduto(ProdutoModel produto) {
	    // Se a lista de tecidos não for nula, cria novos objetos ou usa existentes
	    if (produto.getTecidoModelList() != null) {
	        List<TecidoModel> tecidosAssociados = new ArrayList<>();
	        for (TecidoModel tecido : produto.getTecidoModelList()) {
	            if (tecido.getId() != null) {
	                // Buscar tecido existente pelo ID
	                tecidosAssociados.add(tecidoRepository.findById(tecido.getId())
	                        .orElseThrow(() -> new RuntimeException("Erro: TecidoModel ID " + tecido.getId() + " não encontrado.")));
	            } else {
	                // Criar novo tecido
	                tecidosAssociados.add(tecidoRepository.save(tecido));
	            }
	        }
	        produto.setTecidoModelList(tecidosAssociados);
	    }

	    // Se a lista de fornecedores não for nula, cria novos objetos ou usa existentes
	    if (produto.getForneceModelList() != null) {
	        List<FornecedorModel> fornecedoresAssociados = new ArrayList<>();
	        for (FornecedorModel fornecedor : produto.getForneceModelList()) {
	            if (fornecedor.getId() != null) {
	                // Buscar fornecedor existente pelo ID
	                fornecedoresAssociados.add(fornecedorRepository.findById(fornecedor.getId())
	                        .orElseThrow(() -> new RuntimeException("Erro: FornecedorModel ID " + fornecedor.getId() + " não encontrado.")));
	            } else {
	                // Criar novo fornecedor
	                fornecedoresAssociados.add(fornecedorRepository.save(fornecedor));
	            }
	        }
	        produto.setForneceModelList(fornecedoresAssociados);
	    }

	    // Se a lista de estoque não for nula, cria novos objetos ou usa existentes
	    if (produto.getEstoqueModelList() != null) {
	        List<EstoqueModel> estoquesAssociados = new ArrayList<>();
	        for (EstoqueModel estoque : produto.getEstoqueModelList()) {
	            if (estoque.getId() != null) {
	                // Buscar estoque existente pelo ID
	                estoquesAssociados.add(estoqueRepository.findById(estoque.getId())
	                        .orElseThrow(() -> new RuntimeException("Erro: EstoqueModel ID " + estoque.getId() + " não encontrado.")));
	            } else {
	                // Criar novo estoque
	                estoquesAssociados.add(estoqueRepository.save(estoque));
	            }
	        }
	        produto.setEstoqueModelList(estoquesAssociados);
	    }

	    // Salvar o produto no banco
	    return repository.save(produto);
	}

	public ProdutoModel buscarProdutoPorId(Long id) {
	    if (id == null) {
	        throw new IllegalArgumentException("O ID não pode ser nulo.");
	    }
	    
	    return repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Produto com ID " + id + " não encontrado."));
	}
	
	public List<ProdutoModel> buscarTodosOsProduto() {
		return repository.findAll();
	}
	
}
