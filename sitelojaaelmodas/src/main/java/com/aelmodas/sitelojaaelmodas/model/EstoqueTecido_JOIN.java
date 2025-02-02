package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tecido_model_estoque_model")
public class EstoqueTecido_JOIN implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estoque_model_id", nullable = false)
    @JsonManagedReference // Evita a serialização circular
    private EstoqueModel estoqueModel;

    @ManyToOne
    @JoinColumn(name = "tecido_model_id", nullable = false)
    @JsonManagedReference // Evita a serialização circular
    private TecidoModel tecidoModel;

    public EstoqueTecido_JOIN() {}

    public EstoqueTecido_JOIN(EstoqueModel estoqueModel, TecidoModel tecidoModel) {
        this.estoqueModel = estoqueModel;
        this.tecidoModel = tecidoModel;
    }


	// Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstoqueModel getEstoqueModel() {
        return estoqueModel;
    }

    public void setEstoqueModel(EstoqueModel estoqueModel) {
        this.estoqueModel = estoqueModel;
    }

    public TecidoModel getTecidoModel() {
        return tecidoModel;
    }

    public void setTecidoModel(TecidoModel tecidoModel) {
        this.tecidoModel = tecidoModel;
    }
	
}
