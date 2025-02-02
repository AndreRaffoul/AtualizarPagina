package com.aelmodas.sitelojaaelmodas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;
import com.aelmodas.sitelojaaelmodas.model.EstoqueTecido_JOIN;
import com.aelmodas.sitelojaaelmodas.model.TecidoModel;
import com.aelmodas.sitelojaaelmodas.repository.EstoqueRepository;
import com.aelmodas.sitelojaaelmodas.repository.EstoqueTecidoJOINRepository;
import com.aelmodas.sitelojaaelmodas.repository.TecidoRepository;

@Service
public class EstoqueTecidoJOINService {

	@Autowired
	private EstoqueTecidoJOINRepository estoqueTecidoJOINRepository;
	
	@Autowired
    private EstoqueRepository estoqueModelRepository;

    @Autowired
    private TecidoRepository tecidoModelRepository;
	
    public EstoqueModel salvarComTecidos(EstoqueModel estoqueModel) {
        // Salvar o EstoqueModel
        EstoqueModel estoqueSalvo = estoqueModelRepository.save(estoqueModel);

        // Iterar pela lista de EstoqueTecido_JOIN para processar os tecidos
        for (EstoqueTecido_JOIN estoqueTecidoJOIN : estoqueModel.getEstoqueTecidoList()) {
            TecidoModel tecidoModel = estoqueTecidoJOIN.getTecidoModel();

            if (tecidoModel.getNome() == null || tecidoModel.getNome().isBlank()) {
                throw new RuntimeException("O campo 'nome' do TecidoModel não pode ser nulo ou vazio.");
            }
            
            // Verificar se o tecido já existe pelo nome, caso contrário salvar um novo
            TecidoModel tecidoSalvo = tecidoModelRepository.findByNome(tecidoModel.getNome())
                    .orElseGet(() -> {
                        // Certifique-se de que o objeto TecidoModel está configurado corretamente
                        if (tecidoModel.getNome() == null || tecidoModel.getNome().isBlank()) {
                            throw new RuntimeException("O campo 'nome' do TecidoModel não pode ser nulo ou vazio.");
                        }
                        return tecidoModelRepository.save(tecidoModel);
                    });

            // Configurar o relacionamento na tabela intermediária
            EstoqueTecido_JOIN novoEstoqueTecido = new EstoqueTecido_JOIN();
            novoEstoqueTecido.setEstoqueModel(estoqueSalvo);
            novoEstoqueTecido.setTecidoModel(tecidoSalvo);

            // Salvar na tabela *_JOIN
            estoqueTecidoJOINRepository.save(novoEstoqueTecido);
        }

        return estoqueSalvo;
    }
	
	 // Salvar ou atualizar
    public EstoqueTecido_JOIN salvarOuAtualizar(EstoqueTecido_JOIN estoqueTecido) {
        return estoqueTecidoJOINRepository.save(estoqueTecido);
    }
    
    public List<EstoqueTecido_JOIN> buscarTodos() {
        List<EstoqueTecido_JOIN> lista = estoqueTecidoJOINRepository.findAll();
        lista.forEach(join -> {
            if (join.getEstoqueModel() != null) {
                join.getEstoqueModel().setEstoqueTecidoList(null);
            }
            if (join.getTecidoModel() != null) {
                join.getTecidoModel().setEstoqueList(null);
            }
        });
        return lista;
    }

    // Consultar por ID
    public Optional<EstoqueTecido_JOIN> buscarPorId(Long id) {
        return estoqueTecidoJOINRepository.findById(id);
    }

    // Deletar por ID
    public void deletarPorId(Long id) {
    	estoqueTecidoJOINRepository.deleteById(id);
    }

	
	
}
