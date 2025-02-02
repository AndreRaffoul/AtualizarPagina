package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TecidoModel implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long idTecido;
	private String nome;
	
	@OneToMany(mappedBy = "tecidoModel")
	@JsonManagedReference // Evita a serialização circular
    private List<EstoqueTecido_JOIN> estoqueTecidoList;
	
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

	public List<EstoqueTecido_JOIN> getEstoqueList() {
		return estoqueTecidoList;
	}

	public void setEstoqueList(List<EstoqueTecido_JOIN> estoqueList) {
		this.estoqueTecidoList = estoqueList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
