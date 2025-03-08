package com.aelmodas.sitelojaaelmodas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aelmodas.sitelojaaelmodas.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository< ProdutoModel, Long > {

	/*{
		  "nomeProduto": "Camiseta Estampada",
		  "descricao": "Camiseta 100% algodão com estampa moderna.",
		  "categoria": "Vestuário",
		  "marca": "MarcaExemplo",
		  "cor": "Azul",
		  "tamanho": "M",
		  "genero": "Unissex",
		  "tipo": "Casual",
		  "material": "Algodão",
		  "quantidade": 100,
		  "imagem": "assets/images/camiseta.png",
		  "dataCadastro": "2025-02-14",
		  "dataVencimento": "2025-12-31",
		  "observacoes": "Produto de alta demanda.",
		  "desconto": 10.5,
		  "precoCusto": 20.0,
		  "precoVenda": 40.0,
		  "precoPromocao": 35.0,
		  "tecidoModelList": [
		    {
		      "id": 1,
		      "nome": "Algodão"
		    }
		  ],
		  "estoqueModelList": [
		    {
		      "id": 1,
		      "produto": "Camiseta Estampada",
		      "pluzSize": false,
		      "valorCompra": 18.0,
		      "valorRevenda": 38.0,
		      "dataCompra": "2025-02-01",
		      "observacoes": "Estoque inicial"
		    }
		  ],
		  "forneceModelList": [
		    {
		      "id": 1,
		      "nomeFornecedor": "Fornecedor Exemplo",
		      "email": "contato@fornecedor.com",
		      "telefone": "123456789",
		      "endereco": "Rua Exemplo, 123",
		      "cidade": "Cidade Exemplo",
		      "estado": "EstadoExemplo",
		      "cep": "12345-678",
		      "cnpj": "12.345.678/0001-90"
		    }
		  ]
		} - Criar método para buscarTodosOsProduto obedecendo esta estrutura de json*/
	
	// Método para buscar todos os produtos
	List<ProdutoModel> findAll();
	
}
