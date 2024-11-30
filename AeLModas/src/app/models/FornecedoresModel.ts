import { CategoriasModel } from './CategoriasModel';

export class FornecedoresModel {
    id!: number;
    nomeFornecedor!: string;
    email!: string;
    telefone!: string;
    endereco!: string;
    cidade!: string;
    estado!: string;
    cep!: string;
    cnpj!: string;

    CategoriasModel!: CategoriasModel[];

    constructor(id: number, nomeFornecedor: string, email: string, telefone: string, endereco: string, cidade: string, estado: string, cep: string, cnpj: string) {
        this.id = id;
        this.nomeFornecedor = nomeFornecedor;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.cnpj = cnpj;
    }

    getId() {
        return this.id;
    }

    setId(id: number) {
        this.id = id;
    }

    getNomeFornecedor() {
        return this.nomeFornecedor;
    }

    setNomeFornecedor(nomeFornecedor: string) {
        this.nomeFornecedor = nomeFornecedor;
    }

    getEmail() {
        return this.email;
    }

    setEmail(email: string) {
        this.email = email;
    }

    getTelefone() {
        return this.telefone;
    }

    setTelefone(telefone: string) {
        this.telefone = telefone;
    } 

    getEndereco() {
        return this.endereco;
    }

    setEndereco(endereco: string) {
        this.endereco = endereco;
    } 

    getCidade() {
        return this.cidade;
    }

    setCidade(cidade: string) {
        this.cidade = cidade;
    }

    getEstado() {
        return this.estado;
    }

    setEstado(estado: string) {
        this.estado = estado;
    } 

    getCep() {
        return this.cep;
    }

    setCep(cep: string) {
        this.cep = cep;
    }

    getCnpj() {
        return this.cnpj;
    }

    setCnpj(cnpj: string) {
        this.cnpj = cnpj;
    }

    getCategoriasModel() {
        return this.CategoriasModel;
    }

    setCategoriasModel(CategoriasModel: []) {
        this.CategoriasModel = CategoriasModel;
    }

    // Método para adicionar uma categoria ao fornecedor
    addCategoria(categoria: CategoriasModel) {
        this.CategoriasModel.push(categoria);
    }

}