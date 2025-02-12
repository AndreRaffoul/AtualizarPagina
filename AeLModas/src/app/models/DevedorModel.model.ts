import { EstoqueTecidoJoinModel } from "./EstoqueTecidoJoin.model";

  export class DevedorModel {
    id: number;
    nomeDevedor!: string;
    email!: string;
    telefone!: string;
    endereco!: string;
    cpf!: string;
    divida!: string;
    dataDivida!: string;
    dataVencimento!: string;
    statusDivida!: string;
    observacoes!: string;
    preco!: number;
    quantidade!: number;
    estoqueTecidoList: Array<EstoqueTecidoJoinModel> = [
      new EstoqueTecidoJoinModel({
        id: 0,
        estoqueModel: {
          id: 0,
          produto: '',
          quantidade: 0,
          preco: 0
        },
        tecidoModel: {
          id: 0,
          nome: '',
          cor: '',
          composicao: '',
          precoMetro: 0
        }
      })
    ];

    constructor(devedor: DevedorModel) {
      this.id = devedor.id;
      this.nomeDevedor = devedor.nomeDevedor;
      this.email = devedor.email;
      this.telefone = devedor.telefone;
      this.endereco = devedor.endereco;
      this.cpf = devedor.cpf;
      this.divida = devedor.divida;
      this.dataDivida = devedor.dataDivida;
      this.dataVencimento = devedor.dataVencimento;
      this.statusDivida = devedor.statusDivida;
      this.observacoes = devedor.observacoes;
      this.preco = devedor.preco;
      this.quantidade = devedor.quantidade;
      this.estoqueTecidoList = devedor.estoqueTecidoList;
    }

  }