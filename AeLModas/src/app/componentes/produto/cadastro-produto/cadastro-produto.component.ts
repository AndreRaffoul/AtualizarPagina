import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { timeout } from 'rxjs';
import { ProdutoModel } from '../../../models/ProdutoModel.model';
import { ProdutoService } from '../../../services/produto.service';
import { ListagemProdutoComponent } from '../listagem-produto/listagem-produto.component';

@Component({
  selector: 'app-cadastro-produto',
  standalone: true,
  imports: [
    FormsModule,
    HttpClientModule,
    CommonModule,
    ListagemProdutoComponent
  ],
  templateUrl: './cadastro-produto.component.html',
  styleUrl: './cadastro-produto.component.css',
  providers: [ProdutoService]
})
export class CadastroProdutoComponent implements OnInit {

  editando: boolean = false;
  novoProduto: ProdutoModel = new ProdutoModel(
    0, '', '', '', '', '', '', '', '', '', 0, '', new Date(), new Date(), '', 0, 0, 0, 0, [], [], []
  );
  dataSource: ProdutoModel[] = [];

  constructor(
    private produtoService: ProdutoService
  ) { }

  ngOnInit(): void {
    this.getBuscarTodosOsProdutos();
  }

  getBuscarTodosOsProdutos(): void {
    this.produtoService.getBuscarTodosOsProdutos().subscribe((produtos: ProdutoModel[]) => {
      this.dataSource = produtos;
    }, error => {
      console.error('Erro ao carregar produtos:', error);
    });
  }

  carregarProdutoParaEdicao(id: number): void {
    this.produtoService.getBuscarProdutoPorId(id)
      .pipe(timeout(5000))
      .subscribe(
        (produto) => {
          if (produto && produto.id !== null && produto.id !== undefined && !Number.isNaN(produto.id)) {
            console.log('Produto carregado para edição:', produto);
  
            this.novoProduto = { ...produto };
            this.editando = true;
  
          } else {
            console.error('Erro: Tentativa de editar um produto sem ID válido.');
            alert('Erro: O produto selecionado não possui um ID válido.');
          }
        },
        (error) => {
          console.error('Erro ao buscar produto:', error);
          alert('Erro ao buscar produto.');
        }
      );
      alert('Deseja editar este produto?');
  }
  
   salvarProduto(): void {
    if (this.editando) {
      if (!this.novoProduto.id || this.novoProduto.id === 0
        || this.novoProduto.id === undefined || this.novoProduto.id === null
        || Number.isNaN(this.novoProduto.id)
      ) {
        console.error("Erro: Tentativa de atualizar um fornecedor sem ID válido.");
        alert("Deseja editar esse fornecedor?");
        return;
      }

      this.produtoService.putAtualizarProdutoPorId(this.novoProduto)
      .subscribe((produto) => {
        console.log('Produto atualizado:', produto);
        alert('Produto atualizado com sucesso!');
      }, (error) => {
        console.error('Erro ao atualizar produto:', error);
        alert('Erro ao atualizar produto.');
      });

    } else {
      this.produtoService.postCadastrarProduto(this.novoProduto)
      .subscribe((produto) => {
        console.log('Produto cadastrado:', produto);
        alert('Produto cadastrado com sucesso!');
      }, (error) => {
        console.error('Erro ao cadastrar produto:', error);
        alert('Erro ao cadastrar produto.');
      });
    }
  }

  getBuscarProdutoPorId(id: number): void {
    this.produtoService.getBuscarProdutoPorId(id)
    .subscribe((produto) => {
      console.log('Produto encontrado:', produto);
    }, error => {
      console.error('Erro ao buscar produto:', error);
    });
  }

  resetForm(): void {
    this.novoProduto = new ProdutoModel(0, '', '', '', '', '', '', '', '', '', 0, '', new Date(), new Date(), '', 0, 0, 0, 0, [], [], []);
    this.editando = false;
  }

}
