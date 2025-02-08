import { EstoqueModel } from "./EstoqueModel";
import { TecidoModel } from "./TecidoModel";

export class EstoqueTecidoJoinModel {
  id: number;
  estoqueModel: EstoqueModel;
  tecidoModel: TecidoModel;

  constructor(data: any) {
    if (!data) {
      throw new Error("Erro: Tentativa de criar EstoqueTecidoJoin sem dados válidos.");
    }

    this.id = data.id ?? 0;
    this.estoqueModel = new EstoqueModel(data.estoqueModel ?? {});
    
    // Se tecidoModel for null ou undefined, cria um objeto vazio corretamente
    this.tecidoModel = new TecidoModel(data.tecidoModel);
  }
}