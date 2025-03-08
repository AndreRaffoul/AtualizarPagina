package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EstoqueModel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String produto;
    private Boolean pluzSize;
    private Double valorCompra;
    private Double valorRevenda;
    private LocalDate dataCompra;
    private String observacoes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("estoqueModel")
    private List<EstoqueTecidoJoin> estoqueTecidoList;
    
    /*
     * @OneToMany(mappedBy = "tecidoModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("tecidoModel") 
     */
    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonIgnoreProperties("estoqueModelList")
    private ProdutoModel produtoModel;

    public EstoqueModel() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProduto() { return produto; }
    public void setProduto(String produto) { this.produto = produto; }
    public Boolean getPluzSize() { return pluzSize; }
    public void setPluzSize(Boolean pluzSize) { this.pluzSize = pluzSize; }
    public Double getValorCompra() { return valorCompra; }
    public void setValorCompra(Double valorCompra) { this.valorCompra = valorCompra; }
    public Double getValorRevenda() { return valorRevenda; }
    public void setValorRevenda(Double valorRevenda) { this.valorRevenda = valorRevenda; }
    public LocalDate getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDate dataCompra) { this.dataCompra = dataCompra; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public List<EstoqueTecidoJoin> getEstoqueTecidoList() { return estoqueTecidoList; }
    public void setEstoqueTecidoList(List<EstoqueTecidoJoin> estoqueTecidoList) { this.estoqueTecidoList = estoqueTecidoList; }
}

