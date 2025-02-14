import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { timeout } from 'rxjs';
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

  salvarDevedores(): void{
    if(this.editando){
      if(!this.novoDevedor.id || this.novoDevedor.id == 0 ||
        this.novoDevedor.id === undefined || this.novoDevedor.id === null ||
        Number.isNaN(this.novoDevedor.id)) {
        console.error('ID do devedor não informado ou inválido.');
        alert('ID do devedor não informado ou inválido.');
        return;
      }

      this.devedorService.putDevedorPorId(this.novoDevedor.id, this.novoDevedor)
        .subscribe((devedorAtualizado) => {
          console.log('Devedor atualizado:', devedorAtualizado);
          alert('Devedor atualizado com sucesso!');
          this.resetForm();
        }, (error) => {
          console.error('Erro ao atualizar devedor:', error);
          alert('Erro ao atualizar devedor!');
        });

    } else {
      this.devedorService.saveDevedor(this.novoDevedor)
        .subscribe((devedorSalvo) => {
          console.log('Devedor salvo:', devedorSalvo);
          alert('Devedor salvo com sucesso!');
          this.resetForm();
        }, (error) => {
          console.error('Erro ao salvar devedor:', error);
          alert('Erro ao salvar devedor!');
        });
    }
  }

  /*<app-listagem-devedor (estoqueSelecionado)="carregarDevedoresParaEdicao($event)"></app-listagem-devedor>*/
  carregarDevedoresParaEdicao(devedor: DevedorModel) {

    this.devedorService.getDevedorPorId(devedor.id).pipe(timeout(5000))
    .subscribe((devedorEncontrado) => {
      if(devedorEncontrado !== null || devedorEncontrado !== undefined ||
        Number.isNaN(devedorEncontrado)) {
          console.log('Devedor encontrado:', devedorEncontrado);
          this.novoDevedor = new DevedorModel(devedorEncontrado);
          this.editando = true;}
      else {
        console.error('Devedor não encontrado.');
        alert('Devedor não encontrado.');
      }
    }, (error) => {
      console.error('Erro ao buscar devedor:', error);
      alert('Erro ao buscar devedor!');
    });
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

  resetForm() {
    this.novoDevedor = this.criarNovoDevedor();
    this.editando = false;
  }

}
