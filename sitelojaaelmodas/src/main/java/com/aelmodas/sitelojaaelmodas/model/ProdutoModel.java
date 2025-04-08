package com.aelmodas.sitelojaaelmodas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProdutoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeProduto;
	private String descricao;
	private String categoria;
	private String marca;
	private String cor;
	private String tamanho;
	private String genero;
	private String tipo;
	private String material;
	private Integer quantidade;
	private String imagem;
	private LocalDate dataCadastro;
	private LocalDate dataVencimento;
	private String observacoes;
	private Double desconto;	
	private Double precoCusto;
	private Double precoVenda;
	private Double precoPromocao;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "produto_tecido",
               joinColumns = @JoinColumn(name = "produto_id"),
               inverseJoinColumns = @JoinColumn(name = "tecido_id"))
	private List<TecidoModel> tecidoModelList;
	
	@OneToMany(mappedBy = "produtoModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("produtoModel")
	private List<EstoqueModel> estoqueModelList;
	
	@ManyToMany
    @JoinTable(name = "produto_fornecedor",
               joinColumns = @JoinColumn(name = "produto_id"),
               inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
	@JsonManagedReference	
	private List<FornecedorModel> forneceModelList;
	
	public ProdutoModel() {}
	
	public ProdutoModel(Long id, String nomeProduto, String descricao, String categoria, String marca, String cor,
			String tamanho, String genero, String tipo, String material, Integer quantidade, String imagem,
			Boolean status, LocalDate dataCadastro, LocalDate dataVencimento, String observacoes, Double desconto,
			Double precoCusto, Double precoVenda, Double precoPromocao, List<TecidoModel> tecidoModelList,
			List<EstoqueModel> estoqueModelList, List<FornecedorModel> forneceModelList) {
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.descricao = descricao;
		this.categoria = categoria;
		this.marca = marca;
		this.cor = cor;
		this.tamanho = tamanho;
		this.genero = genero;
		this.tipo = tipo;
		this.material = material;
		this.quantidade = quantidade;
		this.imagem = imagem;
		this.dataCadastro = dataCadastro;
		this.dataVencimento = dataVencimento;
		this.observacoes = observacoes;
		this.desconto = desconto;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.precoPromocao = precoPromocao;
		this.tecidoModelList = tecidoModelList;
		this.estoqueModelList = estoqueModelList;
		this.forneceModelList = forneceModelList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPrecoPromocao() {
		return precoPromocao;
	}

	public void setPrecoPromocao(Double precoPromocao) {
		this.precoPromocao = precoPromocao;
	}

	public List<TecidoModel> getTecidoModelList() {
		return tecidoModelList;
	}

	public void setTecidoModelList(List<TecidoModel> tecidoModelList) {
		this.tecidoModelList = tecidoModelList;
	}

	public List<EstoqueModel> getEstoqueModelList() {
		return estoqueModelList;
	}

	public void setEstoqueModelList(List<EstoqueModel> estoqueModelList) {
		this.estoqueModelList = estoqueModelList;
	}

	public List<FornecedorModel> getForneceModelList() {
		return forneceModelList;
	}

	public void setForneceModelList(List<FornecedorModel> forneceModelList) {
		this.forneceModelList = forneceModelList;
	}
	
}
