package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TecidoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "tecidoModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("tecidoModel") // ✅ Corrigido para evitar erro no relacionamento
    private List<EstoqueTecidoJoin> estoqueTecidoList;

    public TecidoModel() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List<EstoqueTecidoJoin> getEstoqueTecidoList() { return estoqueTecidoList; }
    public void setEstoqueTecidoList(List<EstoqueTecidoJoin> estoqueTecidoList) { this.estoqueTecidoList = estoqueTecidoList; }
}

