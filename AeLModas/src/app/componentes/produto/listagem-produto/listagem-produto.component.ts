import { HttpClientModule } from '@angular/common/http';
import { Component, CUSTOM_ELEMENTS_SCHEMA, EventEmitter, OnInit, Output } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { ProdutoModel } from '../../../models/ProdutoModel.model';
import { ProdutoService } from '../../../services/produto.service';

@Component({
  selector: 'app-listagem-produto',
  standalone: true,
  imports: [
    MatTableModule,
    MatIconModule,
    HttpClientModule,
  ],
  templateUrl: './listagem-produto.component.html',
  styleUrls: ['./listagem-produto.component.css'],
  providers: [ProdutoService],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class ListagemProdutoComponent implements OnInit {

  @Output() produtoSelecionado = new EventEmitter<number>();

  displayedColumns: string[] = ['id', 'nomeProduto', 'descricao', 'categoria', 'marca', 'tamanho',
    'quantidade', 'precoCusto', 'precoVenda', 'estoqueModelList', 'editar', 'deletar'];
  dataSource: ProdutoModel[] = [];

  constructor(
    private produtoService: ProdutoService
  ) { }

  ngOnInit(): void {
    this.getBuscarTodosOsProdutos();
  }

  selecionarProduto(id: number): void {
    console.log('Produto selecionado:', id);
    this.produtoSelecionado.emit(id);
  }

  getBuscarTodosOsProdutos(): void {
    this.produtoService.getBuscarTodosOsProdutos()
    .subscribe( (produtos: ProdutoModel[]) => {
        this.dataSource = produtos
        .map(produto => new ProdutoModel(
          produto.id,
          produto.nomeProduto,
          produto.descricao,
          produto.categoria,
          produto.marca,
          produto.cor,
          produto.tamanho,
          produto.genero,
          produto.tipo,
          produto.material,
          produto.quantidade,
          produto.imagem,
          produto.dataCadastro,
          produto.dataVencimento,
          produto.observacoes,
          produto.desconto,
          produto.precoCusto,
          produto.precoVenda,
          produto.precoPromocao,
          produto.tecidoModelList,
          produto.estoqueModelList,
          produto.forneceModelList
        ));
      }, (error) => { console.error('Erro ao carregar produtos:', error); }
    );
  }

  deletarProdutoPorId(id: number): void {
    if (!id || id <= 0 || Number.isNaN(id)) {
      console.error('Erro: Tentativa de deletar um produto sem ID válido.');
      alert('Erro: O produto selecionado não possui um ID válido.');
      return;
    }
  
    this.produtoService.deletarProdutoPorId(id).subscribe({
      next: (response: any) => {
        console.log(response.message || 'Produto deletado com sucesso.');
        alert(response.message || 'Produto deletado com sucesso.');
        this.getBuscarTodosOsProdutos(); // Atualiza a lista após deletar
      },
      error: (error) => {
        console.error('Erro ao deletar produto:', error);
        alert(error.error?.error || 'Erro ao deletar o produto.');
      }
    });
  }


}
