export class TecidoModel {
  id: number;
  nome: string;

  constructor(data?: any) { // Torna `data` opcional
    data = data || {}; // Garante que `data` nunca seja null ou undefined
    this.id = data.id ?? 0;
    this.nome = data.nome ?? '';
  }
}
