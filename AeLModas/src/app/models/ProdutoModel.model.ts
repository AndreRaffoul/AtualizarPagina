import { IProdutoModel } from "../interfaces/IProdutoModel.interface";

export class ProdutoModel implements IProdutoModel {
    id: number;
    nomeProduto!: string;
    preco!: number;
    quantidade!: number;
    descricao!: string;

    constructor(id: number, nomeProduto: string, preco: number, quantidade: number, descricao: string) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }
}