package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class EstoqueModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;
	private String produto;
	private Boolean pluzSize;
	private Double valorCompra;
	private Double valorRevenda;
	private String dataCompra;
	private String observacoes;

	@OneToMany(mappedBy = "estoqueModel")
	@JsonManagedReference // Evita a serialização circular
	private List<EstoqueTecido_JOIN> estoqueTecidoList;
	
//	@ManyToMany
//	@JoinTable(
//	    name = "tecido_model_estoque_model",
//	    joinColumns = @JoinColumn(name = "estoque_model_id"),
//	    inverseJoinColumns = @JoinColumn(name = "tecido_model_id"))
//	private List<TecidoModel> tecidoList;
	
	public EstoqueModel() {}
	
	public EstoqueModel(Long id, String produto, Boolean pluzSize, Double valorCompra, Double valorRevenda,
			String dataCompra, String observacoes) {
		this.id = id;
		this.produto = produto;
		this.pluzSize = pluzSize;
		this.valorCompra = valorCompra;
		this.valorRevenda = valorRevenda;
		this.dataCompra = dataCompra;
		this.observacoes = observacoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Boolean getPluzSize() {
		return pluzSize;
	}

	public void setPluzSize(Boolean pluzSize) {
		this.pluzSize = pluzSize;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Double getValorRevenda() {
		return valorRevenda;
	}

	public void setValorRevenda(Double valorRevenda) {
		this.valorRevenda = valorRevenda;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<EstoqueTecido_JOIN> getEstoqueTecidoList() {
		return estoqueTecidoList;
	}

	public void setEstoqueTecidoList(List<EstoqueTecido_JOIN> estoqueTecidoList) {
		this.estoqueTecidoList = estoqueTecidoList;
	}

}
