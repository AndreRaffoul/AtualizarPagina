package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class TecidoModel implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long idTecido;
	private String nome;
	
	@ManyToMany
	private List<EstoqueModel> estoqueList;	
	
	public TecidoModel() {}

	public TecidoModel(Long idTecido, String nome) {
		this.idTecido = idTecido;
		this.nome = nome;
	}

	public Long getIdTecido() {
		return idTecido;
	}

	public void setIdTecido(Long idTecido) {
		this.idTecido = idTecido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<EstoqueModel> getEstoqueList() {
		return estoqueList;
	}

	public void setEstoqueList(List<EstoqueModel> estoqueList) {
		this.estoqueList = estoqueList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
