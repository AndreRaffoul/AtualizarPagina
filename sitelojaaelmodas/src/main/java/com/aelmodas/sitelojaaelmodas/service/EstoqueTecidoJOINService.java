package com.aelmodas.sitelojaaelmodas.service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.aelmodas.sitelojaaelmodas.model.EstoqueModel;
import com.aelmodas.sitelojaaelmodas.model.EstoqueTecidoJoin;
import com.aelmodas.sitelojaaelmodas.model.TecidoModel;
import com.aelmodas.sitelojaaelmodas.repository.EstoqueRepository;
import com.aelmodas.sitelojaaelmodas.repository.EstoqueTecidoJOINRepository;
import com.aelmodas.sitelojaaelmodas.repository.TecidoRepository;

@Service
public class EstoqueTecidoJOINService {

    @Autowired
    private EstoqueTecidoJOINRepository estoqueTecidoJOINRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private TecidoRepository tecidoRepository;

    @Transactional
    public EstoqueTecidoJoin salvar(EstoqueTecidoJoin estoqueTecidoJoin) {
        if (estoqueTecidoJoin == null || estoqueTecidoJoin.getEstoqueModel() == null || estoqueTecidoJoin.getTecidoModel() == null) {
            throw new IllegalArgumentException("Os dados do Estoque ou Tecido não podem ser nulos.");
        }

        // Verifica e ajusta valores padrão para evitar salvar valores nulos no banco
        EstoqueModel estoque = estoqueTecidoJoin.getEstoqueModel();
        estoque.setProduto(Optional.ofNullable(estoque.getProduto()).orElse("Produto Desconhecido"));
        estoque.setValorCompra(Optional.ofNullable(estoque.getValorCompra()).orElse(0.0));
        estoque.setValorRevenda(Optional.ofNullable(estoque.getValorRevenda()).orElse(0.0));
        estoque.setObservacoes(Optional.ofNullable(estoque.getObservacoes()).orElse("Sem observações"));

        if (estoque.getId() == null) {
            estoque = estoqueRepository.save(estoque);
        } else {
            Optional<EstoqueModel> estoqueExistente = estoqueRepository.findById(estoque.getId());
            if (estoqueExistente.isPresent()) {
                estoque = estoqueExistente.get();
            } else {
                estoque = estoqueRepository.save(estoque);
            }
        }

        TecidoModel tecido = tecidoRepository.findByNome(estoqueTecidoJoin.getTecidoModel().getNome())
            .orElseGet(() -> tecidoRepository.save(estoqueTecidoJoin.getTecidoModel()));

        EstoqueTecidoJoin novoJoin = new EstoqueTecidoJoin(estoque, tecido);
        return estoqueTecidoJOINRepository.save(novoJoin);
    }

    @Transactional
    public List<EstoqueTecidoJoin> salvarTodos(List<EstoqueTecidoJoin> estoqueTecidoList) {
        estoqueTecidoList.forEach(join -> {
            Objects.requireNonNull(join.getEstoqueModel(), "EstoqueModel não pode ser nulo");
            Objects.requireNonNull(join.getTecidoModel(), "TecidoModel não pode ser nulo");

            join.setEstoqueModel(salvarOuBuscarEstoque(join.getEstoqueModel()));
            join.setTecidoModel(salvarOuBuscarTecido(join.getTecidoModel()));
        });

        return estoqueTecidoJOINRepository.saveAll(estoqueTecidoList);
    }

    public List<EstoqueTecidoJoin> buscarTodos() {
        List<EstoqueTecidoJoin> lista = estoqueTecidoJOINRepository.findAll();
        lista.forEach(this::removerCicloSerializacao);
        return lista;
    }

    public Optional<EstoqueTecidoJoin> buscarPorId(Long id) {
        return estoqueTecidoJOINRepository.findById(id)
                .map(join -> {
                    removerCicloSerializacao(join);
                    return join;
                });
    }

    @Transactional
    public void deletarPorId(Long id) {
        estoqueTecidoJOINRepository.deleteById(id);
    }

    // 🔹 Métodos auxiliares

    private EstoqueModel salvarOuBuscarEstoque(EstoqueModel estoqueModel) {
        if (estoqueModel.getId() != null) {
            return estoqueRepository.findById(estoqueModel.getId())
                    .orElseGet(() -> estoqueRepository.save(estoqueModel));
        }
        return estoqueRepository.save(estoqueModel);
    }

    private TecidoModel salvarOuBuscarTecido(TecidoModel tecidoModel) {
        return tecidoRepository.findByNome(tecidoModel.getNome())
                .orElseGet(() -> tecidoRepository.save(tecidoModel));
    }

    private void removerCicloSerializacao(EstoqueTecidoJoin join) {
        if (join.getEstoqueModel() != null) {
            join.getEstoqueModel().setEstoqueTecidoList(null);
        }
        if (join.getTecidoModel() != null) {
            join.getTecidoModel().setEstoqueTecidoList(null);
        }
    }
}
