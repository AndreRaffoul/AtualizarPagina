package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "devedor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DevedorModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nomeDevedor;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String telefone;

	@Column(nullable = false)
	private String endereco;

	@Column(nullable = true)
	private String cpf;

	@Column(nullable = false)
	private String divida;

	@Column(nullable = false)
	private String dataDivida;

	@Column(nullable = false)
	private String dataVencimento;

	@Column(nullable = false)
	private String statusDivida;

	@Column(nullable = true)
	private String observacoes;
	
	@Column(nullable = false)
	private Double preco;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@OneToMany(mappedBy = "devedorModel", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("devedorModel")
	private List<EstoqueTecidoJoin> estoqueTecidoList;
	
	public DevedorModel() {}
	
	public DevedorModel(
			String nomeDevedor, 
			String email, 
			String telefone, 
			String endereco, 
			String cpf, 
			String divida,
            String dataDivida, 
            String dataVencimento, 
            String statusDivida, 
            String observacoes, 
            Double preco,
            Integer quantidade) {
        this.nomeDevedor = nomeDevedor;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.divida = divida;
        this.dataDivida = dataDivida;
        this.dataVencimento = dataVencimento;
        this.statusDivida = statusDivida;
        this.observacoes = observacoes;
        this.preco = preco;
        this.quantidade = quantidade;
        }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDevedor() {
		return nomeDevedor;
	}

	public void setNomeDevedor(String nomeDevedor) {
		this.nomeDevedor = nomeDevedor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDivida() {
		return divida;
	}

	public void setDivida(String divida) {
		this.divida = divida;
	}

	public String getDataDivida() {
		return dataDivida;
	}

	public void setDataDivida(String dataDivida) {
		this.dataDivida = dataDivida;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getStatusDivida() {
		return statusDivida;
	}

	public void setStatusDivida(String statusDivida) {
		this.statusDivida = statusDivida;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public List<EstoqueTecidoJoin> getEstoqueTecidoList() {
		return estoqueTecidoList;
	}
	
	public void setEstoqueTecidoList(List<EstoqueTecidoJoin> estoqueTecidoList) {
		this.estoqueTecidoList = estoqueTecidoList;
	}

	

}
