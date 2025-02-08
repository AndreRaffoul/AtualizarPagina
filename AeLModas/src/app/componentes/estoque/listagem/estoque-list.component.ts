import { HttpClientModule } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { EstoqueTecidoJoinModel } from '../../../models/EstoqueTecidoJoin.model';
import { EstoqueServService } from '../../../services/estoque-serv.service';

@Component({
  selector: 'app-estoque-list',
  standalone: true,
  imports: [MatIconModule, MatTableModule, HttpClientModule],
  templateUrl: './estoque-list.component.html',
  styleUrl: './estoque-list.component.css'
})
export class EstoqueListComponent implements OnInit {

  @Output() estoqueSelecionado = new EventEmitter<EstoqueTecidoJoinModel>();

  estoque: EstoqueTecidoJoinModel[] = [];
  displayedColumns: string[] = ['id', 'produto', 'pluzSize', 'tecido', 'valorCompra', 'valorRevenda', 'dataCompra', 'observacoes', 'editar', 'deletar'];
  dataSource: EstoqueTecidoJoinModel[] = [];

  constructor( 
    private estoqueServService: EstoqueServService,
) { }
  
  ngOnInit(): void {
    this.carregarEstoque();
  }

  carregarEstoque(): void {
    this.estoqueServService.buscarTodos().subscribe((estoque) => {
      this.dataSource = estoque;
    });
  }

  editarEstoque(estoque: EstoqueTecidoJoinModel): void {
    console.log(`Estoque emitido para edição:`, estoque);
    this.estoqueSelecionado.emit(estoque); // 🔹 Agora emitimos o objeto, e não apenas o id.
  }

  deletarEstoque(id: number): void {
    if(confirm("Tem certeza que deseja deletar este produto?")) {
      this.estoqueServService.deletarPorId(id).subscribe(() => {
        this.carregarEstoque();
      });
    }
  }

}



  // editarEstoque(id: number): void {
  //   console.log(`ID emitido para edição: ${id}`);
  //   this.estoqueServService.buscarEstoquePorId(id).subscribe((estoque) => {
  //     if (estoque) {
  //       console.log('Estoque carregado para edição:', estoque);
  //       this.estoqueSelecionado.emit(estoque.id);
  //     } else {
  //       console.error("Erro: Tentativa de editar um estoque sem ID válido.");
  //       alert("Erro: O estoque selecionado não possui um ID válido.");
  //     }
  //   });
  // }