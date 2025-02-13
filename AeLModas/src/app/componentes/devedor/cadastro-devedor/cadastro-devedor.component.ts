import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DevedorModel } from '../../../models/DevedorModel.model';
import { DevedorService } from '../../../services/devedor.service';
import { ListagemDevedorComponent } from '../listagem-devedor/listagem-devedor.component';

@Component({
  selector: 'app-cadastro-devedor',
  standalone: true,
  imports: [
    ListagemDevedorComponent,
    FormsModule,
        HttpClientModule,
        CommonModule],
  templateUrl: './cadastro-devedor.component.html',
  styleUrl: './cadastro-devedor.component.css',
  providers: [DevedorService]
})
export class CadastroDevedorComponent implements OnInit{

  novoDevedor: DevedorModel = this.criarNovoDevedor();
  editando: boolean = false; // 🔹 Flag para indicar se está no modo de edição

  constructor(
    private devedorService: DevedorService
  ) {}

  ngOnInit() {}

  salvarDevedores(){}

  /*<app-listagem-devedor (estoqueSelecionado)="carregarDevedoresParaEdicao($event)"></app-listagem-devedor>*/
  carregarDevedoresParaEdicao(devedor: DevedorModel) {
    console.log('Estoque carregado para edição:', devedor);
    this.novoDevedor = { ...devedor };
    this.editando = true;
  }

  criarNovoDevedor(): DevedorModel {
    return new DevedorModel({
      id: 0,
      nomeDevedor: '',
      email: '',
      telefone: '',
      endereco: '',
      cpf: '',
      divida: '',
      dataDivida: '',
      dataVencimento: '',
      statusDivida: '',
      observacoes: '',
      preco: 0,
      quantidade: 0,
      // estoqueTecidoList: [
      //   new EstoqueTecidoJoinModel({
      //     id: 0,
      //     estoqueModel: {
      //       id: 0,
      //       produto: '',
      //       quantidade: 0,
      //       preco: 0
      //     },
      //     tecidoModel: {
      //       id: 0,
      //       nome: '',
      //       cor: '',
      //       composicao: '',
      //       precoMetro: 0
      //     }
      //   })
      // ]
    });
  }

  limparFormulario() {
    this.novoDevedor = this.criarNovoDevedor();
    this.editando = false;
  }

}
