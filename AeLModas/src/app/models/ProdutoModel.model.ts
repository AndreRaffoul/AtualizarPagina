import { IProdutoModel } from "../interfaces/IProdutoModel.interface";
import { EstoqueModel } from "./EstoqueModel";
import { FornecedoresModel } from "./FornecedoresModel";
import { TecidoModel } from "./TecidoModel";

export class ProdutoModel implements IProdutoModel {
    
    id: number;
    nomeProduto: string;
    descricao: string;
    categoria: string;
    marca: string;
    cor: string;
    tamanho: string;
    genero: string;
    tipo: string;
    material: string;
    quantidade: number;
    imagem: string;
    dataCadastro: Date;
    dataVencimento: Date;
    observacoes: string;
    desconto: number;
    precoCusto: number;
    precoVenda: number;
    precoPromocao: number;
    tecidoModelList: TecidoModel[];
    estoqueModelList: EstoqueModel[];
    forneceModelList: FornecedoresModel[];

    constructor( 
        id: number,
        nomeProduto: string,
        descricao: string,
        categoria: string,
        marca: string,
        cor: string,
        tamanho: string,
        genero: string,
        tipo: string,
        material: string,
        quantidade: number,
        imagem: string,
        dataCadastro: Date,
        dataVencimento: Date,
        observacoes: string,
        desconto: number,
        precoCusto: number,
        precoVenda: number,
        precoPromocao: number,
        tecidoModelList: TecidoModel[],
        estoqueModelList: EstoqueModel[],
        forneceModelList: FornecedoresModel[]
     ){
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

}