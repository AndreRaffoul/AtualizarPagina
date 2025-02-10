import { HttpClientModule } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { ProdutoModel } from '../../../models/ProdutoModel.model';

@Component({
  selector: 'app-listagem-produto',
  standalone: true,
  imports: [
    MatTableModule,
    MatIconModule,
    HttpClientModule,
  ],
  templateUrl: './listagem-produto.component.html',
  styleUrl: './listagem-produto.component.css'
})
export class ListagemProdutoComponent implements OnInit {

  @Output() produtoSelecionado = new EventEmitter<ProdutoModel>();

  displayedColumns: string[] = ['nomeProduto', 'preco', 'quantidade', 'editar', 'deletar'];
  dataSource: any[] = [];

  constructor() { }

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }


}
