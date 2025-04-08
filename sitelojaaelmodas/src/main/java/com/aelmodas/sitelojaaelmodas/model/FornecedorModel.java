package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor_model")
public class FornecedorModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;
	
	private String nomeFornecedor;	
	private String email;
	private String telefone;
	private String endereco;
	private String cidade;
	private String estado;
	private String cep;
	private String cnpj;
	
	// Lista de ProdutoModel
	@ManyToMany(mappedBy = "forneceModelList", cascade = CascadeType.ALL)
	@JsonBackReference
	@JsonIgnoreProperties("forneceModelList")
    private List<ProdutoModel> produtoModelList;
	
	public FornecedorModel() {}
	
	public FornecedorModel(Long id, String nomeFornecedor, String email, String telefone, String endereco,
			String cidade, String estado, String cep, String cnpj) {
		this.id = id;
		this.nomeFornecedor = nomeFornecedor;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ProdutoModel> getProdutoModelList() {
		return produtoModelList;
	}
	
	public void setProdutoModelList(List<ProdutoModel> produtoModelList) {
		this.produtoModelList = produtoModelList;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
