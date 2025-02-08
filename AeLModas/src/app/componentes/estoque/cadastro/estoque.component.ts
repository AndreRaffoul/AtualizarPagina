import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { EstoqueTecidoJoinModel } from '../../../models/EstoqueTecidoJoin.model';
import { EstoqueServService } from '../../../services/estoque-serv.service';
import { EstoqueListComponent } from '../listagem/estoque-list.component';

@Component({
  selector: 'app-estoque',
  standalone: true,
  imports: [
    EstoqueListComponent,
    FormsModule,
    HttpClientModule,
    CommonModule
  ],
  templateUrl: './estoque.component.html',
  styleUrl: './estoque.component.css',
  providers: [EstoqueServService]
})
export class EstoqueComponent implements OnInit {
  
  novoEstoque: EstoqueTecidoJoinModel = this.criarNovoEstoque();
  editando: boolean = false; // 🔹 Flag para indicar se está no modo de edição
  
  constructor( private estoqueServService: EstoqueServService) {}
  ngOnInit() { }

  carregarEstoqueParaEdicao(estoque: EstoqueTecidoJoinModel) {
    console.log('Estoque carregado para edição:', estoque);
    this.novoEstoque = { ...estoque };
    this.editando = true;
  }

  // carregarEstoqueParaEdicao(id: number) {
  //   this.estoqueServService.buscarEstoquePorId(id).subscribe((estoque) => {
  //     if (estoque) {
  //       console.log('Estoque carregado para edição:', estoque);
  //       this.novoEstoque = new EstoqueTecidoJoinModel({
  //         id: estoque.id,
  //         estoqueModel: {
  //           id: estoque.estoqueModel.id,
  //           produto: estoque.estoqueModel.produto,
  //           plusSize: estoque.estoqueModel.pluzSize,
  //           valorCompra: estoque.estoqueModel.valorCompra,
  //           valorRevenda: estoque.estoqueModel.valorRevenda,
  //           dataCompra: estoque.estoqueModel.dataCompra,
  //           observacoes: estoque.estoqueModel.observacoes
  //         },
  //         tecidoModel: {
  //           idTecido: estoque.tecidoModel.id,
  //           nome: estoque.tecidoModel.nome
  //         }
  //       });  
  //       if (estoque.estoqueModel.dataCompra) {
  //         this.novoEstoque.estoqueModel.dataCompra = estoque.estoqueModel.dataCompra;
  //       }  
  //       this.editando = true;
  //     } else {
  //       console.error("Erro: Tentativa de editar um estoque sem ID válido.");
  //       alert("Erro: O estoque selecionado não possui um ID válido.");
  //     }
  //   });
  // }
  
  
  salvarEstoque() {
    console.log("Enviando JSON para o backend:", JSON.stringify(this.novoEstoque, null, 2));

    if (!this.novoEstoque.estoqueModel.produto || !this.novoEstoque.tecidoModel.nome) {
      alert("Erro: Preencha todos os campos obrigatórios antes de salvar.");
      return;
    }

    if (this.editando) {
      // 🔹 Atualizar estoque existente
      this.estoqueServService.atualizarEstoquePorId(this.novoEstoque).subscribe({
        next: (response) => {
          console.log("Estoque atualizado com sucesso!", response);
          this.limparFormulario();
        },
        error: (error) => {
          console.error("Erro ao atualizar estoque:", error);
        }
      });
    } else {
      // 🔹 Criar novo estoque
      this.estoqueServService.salvarEstoque(this.novoEstoque).subscribe({
        next: (response) => {
          console.log("Produto salvo com sucesso!", response);
          this.limparFormulario();
        },
        error: (error) => {
          console.error("Erro ao salvar produto:", error);
        }
      });
    }
  }

  limparFormulario() {
    this.novoEstoque = this.criarNovoEstoque();
    this.editando = false;
  }
  
  formatarData(data: Date | null): string {
    if (!data) return '';
    const dia = data.getDate().toString().padStart(2, '0');
    const mes = (data.getMonth() + 1).toString().padStart(2, '0');
    const ano = data.getFullYear();
    return `${ano}-${mes}-${dia}`;
  }

  criarNovoEstoque(): EstoqueTecidoJoinModel {
    return {
      id: 0,
      estoqueModel: {
        id: 0,
        produto: '',
        pluzSize: false,
        valorCompra: 0,
        valorRevenda: 0,
        dataCompra: new Date(),
        observacoes: ''
      },
      tecidoModel: {
        id: 0,
        nome: ''
      }
    };
  }

}


  // limparFormulario() {
  //   this.novoProduto = '';
  //   this.novoPluzSize = false;
  //   this.novoValorCompra = null;
  //   this.novoValorRevenda = null;
  //   this.novaDataCompra = null;
  //   this.novasObservacoes = '';
  //   this.novoIdTecido = null;
  //   this.novoTecido = '';
  // }

  // salvarEstoque() {
  //   const novoEstoque = new EstoqueTecidoJoinModel({
  //     estoqueModel: {
  //       produto: this.novoProduto?.trim() || 'Produto Desconhecido',
  //       pluzSize: this.novoPluzSize ?? false,
  //       valorCompra: this.novoValorCompra !== undefined ? this.novoValorCompra : 0,
  //       valorRevenda: this.novoValorRevenda !== undefined ? this.novoValorRevenda : 0,
  //       dataCompra: this.novaDataCompra ? this.formatarData(this.novaDataCompra) : new Date().toISOString(),
  //       observacoes: this.novasObservacoes?.trim() || 'Sem observações'
  //     },
  //     tecidoModel: {
  //       idTecido: this.novoIdTecido ?? null,
  //       nome: this.novoTecido?.trim() || 'Tecido não informado'
  //     }
  //   });
  //   console.log("Enviando JSON para o backend:", JSON.stringify(novoEstoque, null, 2));
  //   if(novoEstoque.estoqueModel.produto === '' || novoEstoque.tecidoModel.nome === '' ||
  //     novoEstoque.estoqueModel.valorCompra === 0 || novoEstoque.estoqueModel.valorRevenda === 0 ||
  //     novoEstoque.estoqueModel.dataCompra === null || novoEstoque.estoqueModel.observacoes === '') {
  //     alert("Erro: Preencha todos os campos obrigatórios antes de salvar.");
  //     return;
  //   }
  //   this.estoqueServService.salvarEstoque(novoEstoque).subscribe({
  //     next: (response) => {
  //       console.log("Produto salvo com sucesso!", response);
  //       this.estoqueServService.buscarEstoquePorId(response.id).subscribe((estoque) => {
  //         this.carregarEstoqueParaEdicao(estoque);
  //       });
  //     },
  //     error: (error) => {
  //       console.error("Erro ao salvar produto:", error);
  //     }
  //   });
  // }

// carregarEstoqueParaEdicao(id: number) {
  //   this.estoqueServService.buscarEstoquePorId(id).subscribe((estoque) => {
  //     if (estoque !== null || estoque !== undefined || estoque !== null || Number.isNaN(estoque)) {
  //       console.log('Estoque carregado para edição:', estoque);
  //       this.novoEstoque = new EstoqueTecidoJoinModel({
  //         id: estoque.id,
  //         estoqueModel: {
  //           id: estoque.estoqueModel.id,
  //           produto: estoque.estoqueModel.produto,
  //           plusSize: estoque.estoqueModel.pluzSize,
  //           valorCompra: estoque.estoqueModel.valorCompra,
  //           valorRevenda: estoque.estoqueModel.valorRevenda,
  //           dataCompra: estoque.estoqueModel.dataCompra,
  //           observacoes: estoque.estoqueModel.observacoes
  //         },
  //         tecidoModel: {
  //           idTecido: estoque.tecidoModel.id,
  //           nome: estoque.tecidoModel.nome
  //         }
  //       });
  //       this.editando = true;
  //     } else {
  //       console.error("Erro: Tentativa de editar um produto sem ID válido.");
  //       alert("Erro: O produto selecionado não possui um ID válido.");
  //     }
  //   });
  // }