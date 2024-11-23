
export class CategoriasModel {
    id!: number;
    nomeCategoria!: string;
    descricao!: string;

    constructor(id: number, nomeCategoria: string, descricao: string) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }

    getId() {
        return this.id;
    }

    setId(id: number) {
        this.id = id;
    }

    getNomeCategoria() {
        return this.nomeCategoria;
    }

    setNomeCategoria(nomeCategoria: string) {
        this.nomeCategoria = nomeCategoria;
    }

    getDescricao() {
        return this.descricao;
    }

    setDescricao(descricao: string) {
        this.descricao = descricao;
    }
}