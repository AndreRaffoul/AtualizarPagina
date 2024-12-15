import { TecidoModel } from "./TecidoModel";

export interface EstoqueModel {
  id: number,
  produto: string,
  pluzSize: boolean,
  tecido: Array<TecidoModel>,
  valorCompra: number,
  valorRevenda: number,
  dataCompra: Date,
  observacoes: string
}

export interface EstoqueModel extends Array<EstoqueModel> { };

