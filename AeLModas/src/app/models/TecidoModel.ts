export interface TecidoModel {
  id?: number,
  nome: string,
}

export interface EstoqueModel extends Array<EstoqueModel> { };
