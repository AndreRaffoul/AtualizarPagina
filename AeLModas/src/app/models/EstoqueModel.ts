
export class EstoqueModel {
  id!: number;
  produto!: string;
  pluzSize!: boolean;
  valorCompra!: number;
  valorRevenda!: number;
  dataCompra!: Date;
  observacoes!: string;

  constructor(data: any) {
    if (!data) {
      throw new Error("Erro: Tentativa de criar EstoqueModel sem dados válidos.");
    }

    this.id = data.id ?? 0;
    this.produto = data.produto ?? '';
    this.pluzSize = data.pluzSize ?? false;
    this.valorCompra = data.valorCompra ?? 0;
    this.valorRevenda = data.valorRevenda ?? 0;
    this.dataCompra = data.dataCompra ? new Date(data.dataCompra) : new Date();
    this.observacoes = data.observacoes ?? '';
  }

}

