export interface IProdutoModel {
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
    tecidoModelList: any[];
    estoqueModelList: any[];
    forneceModelList: any[];

}